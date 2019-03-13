package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.CustomerVerificationRequest;
import ug.co.sampletracker.app.models.responses.CustomerVerificationResponse;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;
import ug.co.sampletracker.app.utils.interfaces.ServerResponseCustomerVerificationListener;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTCustomerVerification {

    private ServerResponseCustomerVerificationListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseCustomerVerificationListener responseListener) {
        this.responseListener = responseListener;
    }


    public void fetchCustomerDetails(CustomerVerificationRequest request) {

        Call<CustomerVerificationResponse> call = service.verifyCustomer(request);
        call.enqueue(new Callback<CustomerVerificationResponse>() {
            @Override
            public void onResponse(Call<CustomerVerificationResponse> call, Response<CustomerVerificationResponse> response) {

                handleCustomerDetailsResponse(response);

            }

            @Override
            public void onFailure(Call<CustomerVerificationResponse> call, Throwable t) {

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

    private void handleCustomerDetailsResponse(Response<CustomerVerificationResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseCustomerVerificationSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

}
