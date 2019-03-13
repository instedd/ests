package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.OrderUnrefRequest;
import ug.co.sampletracker.app.models.responses.PaymentUnreferencedResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTPaymentUnReferenced {

    private ServerResponsePayHimaByUnReferencedListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponsePayHimaByUnReferencedListener responseListener) {
        this.responseListener = responseListener;
    }


    public void payHimaByUnReferenced(OrderUnrefRequest request) {

        Call<PaymentUnreferencedResponse> call = service.payHimaUnreferenced(request);
        call.enqueue(new Callback<PaymentUnreferencedResponse>() {
            @Override
            public void onResponse(Call<PaymentUnreferencedResponse> call, Response<PaymentUnreferencedResponse> response) {

                response.body().setPaymentMethod(request.getPaymentMode());
                handlePayHimaByUnReferencedResponse(response);

            }

            @Override
            public void onFailure(Call<PaymentUnreferencedResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handlePayHimaByUnReferencedResponse(Response<PaymentUnreferencedResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponsePayHimaByUnReferencedSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponsePayHimaByUnReferencedListener {

        void serverResponseError(String error);
        void serverResponsePayHimaByUnReferencedSuccess(PaymentUnreferencedResponse response);

    }

}
