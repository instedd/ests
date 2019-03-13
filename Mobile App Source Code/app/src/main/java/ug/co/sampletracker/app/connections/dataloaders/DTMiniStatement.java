package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.StatementRequest;
import ug.co.sampletracker.app.models.responses.StatementResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTMiniStatement {

    private ServerResponseMiniStatementListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();
    private String receiptionType;

    public void setResponseListener(ServerResponseMiniStatementListener responseListener) {
        this.responseListener = responseListener;
    }


    public void miniStatement(StatementRequest request) {

        receiptionType = request.receptionType;
        Call<StatementResponse> call = service.ministatement(request);
        call.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {

                handleMiniStatementResponse(response);

            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {

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

    private void handleMiniStatementResponse(Response<StatementResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            StatementResponse statementResponse = response.body();
            statementResponse.setReceiptionType(receiptionType);
            responseListener.serverResponseOrderPlacementSuccess(statementResponse);

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponseMiniStatementListener {

        void serverResponseError(String error);
        void serverResponseOrderPlacementSuccess(StatementResponse response);

    }

}
