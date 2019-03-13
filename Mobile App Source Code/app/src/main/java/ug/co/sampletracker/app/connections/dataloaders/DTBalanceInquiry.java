package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.Balance;
import ug.co.sampletracker.app.models.requests.BalanceRequest;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTBalanceInquiry {

    private ServerResponseBalanceInquiryListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseBalanceInquiryListener responseListener) {
        this.responseListener = responseListener;
    }


    public void balanceInquiry(BalanceRequest request) {

        Call<Balance> call = service.balanceInquiry(request);
        call.enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {

                handleBalanceInquiryResponse(response);

            }

            @Override
            public void onFailure(Call<Balance> call, Throwable t) {

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

    private void handleBalanceInquiryResponse(Response<Balance> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseBalanceInquirySuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponseBalanceInquiryListener{

        void serverResponseError(String error);
        void serverResponseBalanceInquirySuccess(Balance balance);

    }

}
