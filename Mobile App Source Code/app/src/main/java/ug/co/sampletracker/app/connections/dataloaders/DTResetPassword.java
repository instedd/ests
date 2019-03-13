package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.ResetPasswordReq;
import ug.co.sampletracker.app.models.responses.ResetPasswordRes;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTResetPassword {

    private ServerResponseResetPasswordRequestOTPListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseResetPasswordRequestOTPListener responseListener) {
        this.responseListener = responseListener;
    }


    public void resetPassword(ResetPasswordReq request) {

        Call<ResetPasswordRes> call = service.resetPassword(request);
        call.enqueue(new Callback<ResetPasswordRes>() {
            @Override
            public void onResponse(Call<ResetPasswordRes> call, Response<ResetPasswordRes> response) {

                handleResetPasswordResponse(response);

            }

            @Override
            public void onFailure(Call<ResetPasswordRes> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseResetPasswordError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleResetPasswordResponse(Response<ResetPasswordRes> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseResetPasswordSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseResetPasswordError("File not found");
        }else{
            responseListener.serverResponseResetPasswordError("Internal server error");
        }

    }

    public interface ServerResponseResetPasswordRequestOTPListener {

        void serverResponseResetPasswordError(String error);
        void serverResponseResetPasswordSuccess(ResetPasswordRes response);

    }

}
