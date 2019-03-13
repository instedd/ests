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

public class DTUpdateUserInfo {

    private ServerResponseUpdateUserInfoListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseUpdateUserInfoListener responseListener) {
        this.responseListener = responseListener;
    }


    public void updateUser(RegistrationRequest request) {

        Call<RegistrationResponse> call = service.updateUser(request);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                handleUpdateUserInfoResponse(response);

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseUpdateUserInfoError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleUpdateUserInfoResponse(Response<RegistrationResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseUpdateUserInfoSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseUpdateUserInfoError("File not found");
        }else{
            responseListener.serverResponseUpdateUserInfoError("Internal server error");
        }

    }

    public interface ServerResponseUpdateUserInfoListener {

        void serverResponseUpdateUserInfoError(String error);
        void serverResponseUpdateUserInfoSuccess(RegistrationResponse response);

    }

}
