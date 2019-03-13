package ug.co.sampletracker.app.connections.dataloaders;

import java.util.List;

import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.database.DataManager;
import ug.co.sampletracker.app.models.StReceivedSample;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLGetReceivedSample {

    private ServerResponseGetReceivedSamplesListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseGetReceivedSamplesListener responseListener) {
        this.responseListener = responseListener;
    }


    public void getReceivedSamples() {

        responseListener.serverResponseGetReceivedSamplesSuccess(new DataManager().getAppData().getReceivedSamples());

/*
        Call<List<StReceivedSample>> call = service.getReceivedSamples();
        call.enqueue(new Callback<List<StReceivedSample>>() {
            @Override
            public void onResponse(Call<List<StReceivedSample>> call, Response<List<StReceivedSample>> response) {

                *//* Simulate list of received samples *//*
                List<StReceivedSample> receivedSamples = new ArrayList<>();

                StReceivedSample sample1 = new StReceivedSample();
                sample1.setId(1);
                sample1.setSample_id("12345");

                StDestination destination = new StDestination();
                destination.id = "1";
                destination.name = "Bulenga H/C 1";

                sample1.setDestination(destination);

                receivedSamples.add(sample1);

                responseListener.serverResponseGetReceivedSamplesSuccess(receivedSamples);
                *//*End of simulation *//*

                //handleServerResponse(response);
            }

            @Override
            public void onFailure(Call<List<StReceivedSample>> call, Throwable t) {

                String errorMessage;
                if(t instanceof NetworkErrorException || t instanceof IOException){
                    errorMessage = "Network problem occurred. Please check your connection";
                }else{
                    errorMessage = "Something went wrong will we tried processing your request, please try again";
                }

                responseListener.serverResponseError(errorMessage);

                Log.e("Response Failure",String.valueOf(t.getMessage()));

            }
        });*/

    }

    private void handleServerResponse(Response<List<StReceivedSample>> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseGetReceivedSamplesSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponseGetReceivedSamplesListener {

        void serverResponseError(String error);
        void serverResponseGetReceivedSamplesSuccess(List<StReceivedSample> response);

    }

}
