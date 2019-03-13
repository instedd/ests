package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.SampleReceiptionReq;
import ug.co.sampletracker.app.models.responses.ReceiveSampleRes;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLSubmitReceivedSampleSample {

    private ServerResponseSubmitReceivedSampleListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseSubmitReceivedSampleListener responseListener) {
        this.responseListener = responseListener;
    }


    public void submitReceivedSample(SampleReceiptionReq req) {


        Call<ReceiveSampleRes> call = service.submitReceivedSample(

                req.getFinal_destination(),
                req.getSample_status(),
                req.getTransporter(),
                req.getBarcode(),
                req.getFacility_code(),
                req.getIs_destination(),
                req.getDateReceived()

        );

        call.enqueue(new Callback<ReceiveSampleRes>() {
            @Override
            public void onResponse(Call<ReceiveSampleRes> call, Response<ReceiveSampleRes> response) {

                handleServerResponse(response);

            }

            @Override
            public void onFailure(Call<ReceiveSampleRes> call, Throwable t) {

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

    private void handleServerResponse(Response<ReceiveSampleRes> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseSubmitReceivedSampleSuccess(response.body());

        }else if(response.code() == NetworkStatusCodes.CODE_FILE_NOT_FOUND){
            responseListener.serverResponseError("File not found");
        }
        else if(response.code() == NetworkStatusCodes.CODE_FORBIDDEN || response.code() == NetworkStatusCodes.CODE_CONFLICT){

            ResponseBody body = response.errorBody();
            Gson gson = new Gson();
            TypeAdapter<ug.co.sampletracker.app.models.responses.Response> adapter = gson.getAdapter (ug.co.sampletracker.app.models.responses.Response.class);
            try {

                ug.co.sampletracker.app.models.responses.Response response1 = adapter.fromJson(body.string());
                String message = response1.getMessage();
                String formattedMsg = message == null ? "Failed with no error message returned" :
                        message.replace("\n", "").replace("\r", " ").trim().replaceAll(" +", " ");

                response1.setMessage(formattedMsg);
                responseListener.serverResponseError(formattedMsg);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            responseListener.serverResponseError("Internal server error");
        }

    }

    public interface ServerResponseSubmitReceivedSampleListener {

        void serverResponseError(String error);
        void serverResponseSubmitReceivedSampleSuccess(ReceiveSampleRes response);

    }

}
