package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.VerifyPaymentHistoryOTPRequest;
import ug.co.sampletracker.app.models.responses.VerifyPaymentHistoryOTPResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTVerifyOTP {

    private ServerResponseRequestOTPListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseRequestOTPListener responseListener) {
        this.responseListener = responseListener;
    }


    public void verifyOTP(VerifyPaymentHistoryOTPRequest request) {

        Call<VerifyPaymentHistoryOTPResponse> call = service.verifyOTP(request);
        call.enqueue(new Callback<VerifyPaymentHistoryOTPResponse>() {
            @Override
            public void onResponse(Call<VerifyPaymentHistoryOTPResponse> call, Response<VerifyPaymentHistoryOTPResponse> response) {

                handleVerifyOTPResponse(response, request.getPhone());

            }

            @Override
            public void onFailure(Call<VerifyPaymentHistoryOTPResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseDTVerifyOTPError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleVerifyOTPResponse(Response<VerifyPaymentHistoryOTPResponse> response, String phoneNumber) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            VerifyPaymentHistoryOTPResponse historyOTPResponse = response.body();
            historyOTPResponse.setPhone(phoneNumber);
            responseListener.serverResponseVerifyOTPSuccess(historyOTPResponse);

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseDTVerifyOTPError("File not found");
        }else{
            responseListener.serverResponseDTVerifyOTPError("Internal server error");
        }

    }

    public interface ServerResponseRequestOTPListener {

        void serverResponseDTVerifyOTPError(String error);
        void serverResponseVerifyOTPSuccess(VerifyPaymentHistoryOTPResponse response);

    }

}
