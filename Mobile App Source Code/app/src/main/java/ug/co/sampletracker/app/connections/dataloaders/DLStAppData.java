package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.models.responses.AppDataResponse;
import ug.co.sampletracker.app.models.responses.LoginStResponse;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLStAppData {

    private ServerResponseAppDataListener responseListener;
    private LoginStResponse loginStResponse;

    public void setLoginStResponse(LoginStResponse loginStResponse) {
        this.loginStResponse = loginStResponse;
    }

    public LoginStResponse getLoginStResponse() {
        return loginStResponse;
    }

    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseAppDataListener responseListener) {
        this.responseListener = responseListener;
    }


    public void getAppData() {

        Call<AppDataResponse> call = service.getSampleTrackerAppData();
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
            responseListener.serverResponseAppDataSuccess(appDataResponse,loginStResponse);

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){

            responseListener.serverResponseError("File not found");

        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    private void saveAppData(AppDataResponse appDataResponse) {

        new DataManager().saveAppData(appDataResponse);

    }

    public interface ServerResponseAppDataListener {

        void serverResponseError(String error);
        void serverResponseAppDataSuccess(AppDataResponse appDataResponse, LoginStResponse loginStResponse);

    }

}
