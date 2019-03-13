package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.database.DbHandler;
import ug.co.sampletracker.app.models.requests.AppDataRequest;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;
import ug.co.sampletracker.app.utils.constants.StatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DTAppData {

    private ServerResponseAppDataListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseAppDataListener responseListener) {
        this.responseListener = responseListener;
    }


    public void getAppData(AppDataRequest request) {

        Call<AppDataResponse> call = service.getAppData(request);
        call.enqueue(new Callback<AppDataResponse>() {
            @Override
            public void onResponse(Call<AppDataResponse> call, Response<AppDataResponse> response) {

                handleAppDataResponse(response);

            }

            @Override
            public void onFailure(Call<AppDataResponse> call, Throwable t) {

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

    private void handleAppDataResponse(Response<AppDataResponse> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            AppDataResponse appDataResponse = response.body();
            saveAppData(appDataResponse);
            responseListener.serverResponseAppDataSuccess(appDataResponse);

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    private void saveAppData(AppDataResponse appDataResponse) {

        if(appDataResponse.getStatusCode().equalsIgnoreCase(StatusCodes.SUCCESS)){

            DbHandler dbHandler = new DbHandler();
            dbHandler.saveHimaPackagings(appDataResponse.getPackagings());
            dbHandler.saveRegionalProdtPrices(appDataResponse.getRegionalPrices());

        }

    }

    public interface ServerResponseAppDataListener {

        void serverResponseError(String error);
        void serverResponseAppDataSuccess(AppDataResponse appDataResponse);

    }

}
