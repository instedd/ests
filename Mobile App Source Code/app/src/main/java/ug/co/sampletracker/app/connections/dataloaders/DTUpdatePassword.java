package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.RegistrationRequest;
import ug.co.sampletracker.app.models.responses.RegistrationResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTUpdatePassword {

    private ServerResponseUpdatePasswordListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseUpdatePasswordListener responseListener) {
        this.responseListener = responseListener;
    }


    public void UpdatePassword(RegistrationRequest request) {

        Call<RegistrationResponse> call = service.updateUserPassword(request);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                handleUpdatePasswordResponse(response);

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseUpdatePasswordError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleUpdatePasswordResponse(Response<RegistrationResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseUpdatePasswordSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseUpdatePasswordError("File not found");
        }else{
            responseListener.serverResponseUpdatePasswordError("Internal server error");
        }

    }

    public interface ServerResponseUpdatePasswordListener {

        void serverResponseUpdatePasswordError(String error);
        void serverResponseUpdatePasswordSuccess(RegistrationResponse response);

    }

}
