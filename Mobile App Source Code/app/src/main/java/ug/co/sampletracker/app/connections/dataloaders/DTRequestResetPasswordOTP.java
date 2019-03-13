package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.RequestResetPasswordOTPReq;
import ug.co.sampletracker.app.models.responses.RequestResetPasswordOTPRes;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTRequestResetPasswordOTP {

    private ServerResponseRequestResetPasswordOTPRequestOTPListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseRequestResetPasswordOTPRequestOTPListener responseListener) {
        this.responseListener = responseListener;
    }

    public void resetPassword(RequestResetPasswordOTPReq request) {

        Call<RequestResetPasswordOTPRes> call = service.requestResetPasswordOTP(request);
        call.enqueue(new Callback<RequestResetPasswordOTPRes>() {
            @Override
            public void onResponse(Call<RequestResetPasswordOTPRes> call, Response<RequestResetPasswordOTPRes> response) {

                handleResetPasswordResponse(response);

            }

            @Override
            public void onFailure(Call<RequestResetPasswordOTPRes> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseRequestResetPasswordOTPError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleResetPasswordResponse(Response<RequestResetPasswordOTPRes> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseRequestResetPasswordOTPSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseRequestResetPasswordOTPError("File not found");
        }else{
            responseListener.serverResponseRequestResetPasswordOTPError("Internal server error");
        }

    }

    public interface ServerResponseRequestResetPasswordOTPRequestOTPListener {

        void serverResponseRequestResetPasswordOTPError(String error);
        void serverResponseRequestResetPasswordOTPSuccess(RequestResetPasswordOTPRes response);

    }

}
