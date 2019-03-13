package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.StSpecimen;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLSpecimen {

    private ServerResponseGetSpecimenListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseGetSpecimenListener responseListener) {
        this.responseListener = responseListener;
    }


    public void getSpecimen() {

        Call<List<StSpecimen>> call = service.getSpecimenTypes();
        call.enqueue(new Callback<List<StSpecimen>>() {
            @Override
            public void onResponse(Call<List<StSpecimen>> call, Response<List<StSpecimen>> response) {
                handleServerResponse(response);
            }

            @Override
            public void onFailure(Call<List<StSpecimen>> call, Throwable t) {

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

    private void handleServerResponse(Response<List<StSpecimen>> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseSpecimenSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponseGetSpecimenListener {

        void serverResponseError(String error);
        void serverResponseSpecimenSuccess(List<StSpecimen> response);

    }

}
