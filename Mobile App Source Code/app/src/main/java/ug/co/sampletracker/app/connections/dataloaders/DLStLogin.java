package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.LoginStRequest;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.utils.constants.ConstStrings;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLStLogin {

    private ServerResponseLoginListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseLoginListener responseListener) {
        this.responseListener = responseListener;
    }

    public void loginUser(LoginStRequest request) {

        Call<LoginStResponse> call = service.login(request.getTxt_username(),request.getTxt_password());
        call.enqueue(new Callback<LoginStResponse>() {
            @Override
            public void onResponse(Call<LoginStResponse> call, Response<LoginStResponse> response) {

                handleLoginResponse(response);

            }

            @Override
            public void onFailure(Call<LoginStResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseLoginError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleLoginResponse(Response<LoginStResponse> response) {

        LoginStResponse loginStResponse = response.body();

        if(ConstStrings.DEBUG_MODE){

            /* Here probably the body is null so we initialize */
            loginStResponse = new LoginStResponse();
            loginStResponse.setStatus(StatusCodes.SUCCESS);
            loginStResponse.setStatusDescription("SUCCESS");
            responseListener.serverResponseLoginSuccess(loginStResponse);

            return;

        }

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseLoginSuccess(loginStResponse);

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseLoginError("File not found");
        }else if(response.code() == NetworkStatusCodes.CODE_FORBIDDEN){

            ResponseBody body = response.errorBody();
            Gson gson = new Gson();
            TypeAdapter<LoginStResponse> adapter = gson.getAdapter (LoginStResponse.class);
            try {

                LoginStResponse loginStResponse1 = adapter.fromJson(body.string());
                String message = loginStResponse1.getMessage();
                String formattedMsg = message == null ? "Failed with no error message returned" :
                        message.replace("\n", "").replace("\r", " ").trim().replaceAll(" +", " ");

                loginStResponse1.setMessage(formattedMsg);
                responseListener.serverResponseLoginError(formattedMsg);

            } catch (IOException e) {
                e.printStackTrace();
            }


            //  responseListener.serverResponseLoginSuccess(response.loginStResponse());
         //   responseListener.serverResponseLoginError("Forbidden");
        }else{
            responseListener.serverResponseLoginError("Internal server error. Status Code "+response.code());
        }

    }

    public interface ServerResponseLoginListener {

        void serverResponseLoginError(String error);
        void serverResponseLoginSuccess(LoginStResponse response);

    }

}
