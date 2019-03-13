package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.RequestPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.RequestPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTRequestOTP {

    private ServerResponseRequestOTPListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseRequestOTPListener responseListener) {
        this.responseListener = responseListener;
    }


    public void requestOTP(RequestPaymentHistoryOTPRequest request) {

        Call<RequestPaymentHistoryOTPResponse> call = service.requestOTP(request);
        call.enqueue(new Callback<RequestPaymentHistoryOTPResponse>() {
            @Override
            public void onResponse(Call<RequestPaymentHistoryOTPResponse> call, Response<RequestPaymentHistoryOTPResponse> response) {

                handleRequestOTPResponse(response);

            }

            @Override
            public void onFailure(Call<RequestPaymentHistoryOTPResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseDTRequestOTPError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleRequestOTPResponse(Response<RequestPaymentHistoryOTPResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseRequestOTPSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseDTRequestOTPError("File not found");
        }else{
            responseListener.serverResponseDTRequestOTPError("Internal server error");
        }

    }

    public interface ServerResponseRequestOTPListener {

        void serverResponseDTRequestOTPError(String error);
        void serverResponseRequestOTPSuccess(RequestPaymentHistoryOTPResponse response);

    }

}
