package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.GetTransactionStatusRequest;
import ug.co.sampletracker.app.models.responses.GetTransactionStatusResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTGetTransactionStatus {

    private ServerResponseGetTransStatusListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseGetTransStatusListener responseListener) {
        this.responseListener = responseListener;
    }


    public void getTransactionStatus(GetTransactionStatusRequest request) {

        Call<GetTransactionStatusResponse> call = service.getTransactionStatus(request);
        call.enqueue(new Callback<GetTransactionStatusResponse>() {
            @Override
            public void onResponse(Call<GetTransactionStatusResponse> call, Response<GetTransactionStatusResponse> response) {

                handleGetStatusResponse(response);

            }

            @Override
            public void onFailure(Call<GetTransactionStatusResponse> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseTransStatusError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });

    }

    private void handleGetStatusResponse(Response<GetTransactionStatusResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseTransStatusSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseTransStatusError("File not found");
        }else{
            responseListener.serverResponseTransStatusError("Internal server error");
        }

    }

    public interface ServerResponseGetTransStatusListener {

        void serverResponseTransStatusError(String error);
        void serverResponseTransStatusSuccess(GetTransactionStatusResponse response);

    }

}
