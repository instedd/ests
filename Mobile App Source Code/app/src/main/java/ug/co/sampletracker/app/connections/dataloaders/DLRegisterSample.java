package ug.co.sampletracker.app.connections.dataloaders;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi;
import ug.co.sampletracker.app.connections.retrofit.RetrofitService;
import ug.co.sampletracker.app.models.requests.SampleRegistrationReq;
import ug.co.sampletracker.app.models.responses.RegisterSampleRes;
import ug.co.sampletracker.app.utils.constants.EndPointMethods;
import ug.co.sampletracker.app.utils.constants.NetworkStatusCodes;

import static ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi.createPartFromString;
import static ug.co.sampletracker.app.connections.retrofit.LocalRetrofitApi.prepareFilePart;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 5/6/2018.
 */

public class DLRegisterSample {

    private ServerResponseRegisterSampleListener responseListener;
    private RetrofitService service = (new LocalRetrofitApi(EndPointMethods.BASE_URL)).getRetrofitService();

    public void setResponseListener(ServerResponseRegisterSampleListener responseListener) {
        this.responseListener = responseListener;
    }


    public void registerSample(SampleRegistrationReq req, Context context) {


        // create part for file (photo, video, ...)
        MultipartBody.Part body = prepareFilePart("uploadFile", req.formData.getFile(),
                req.formData.getUri(),context);

        // create a map of data to pass along
        RequestBody initialSampleDate = createPartFromString(req.initialSampleDate);
        RequestBody finalDestinationDate = createPartFromString(req.finalDestinationDate);
        RequestBody facility_code = createPartFromString(String.valueOf(req.facility_code));
        RequestBody barcode = createPartFromString(req.barcode);
        RequestBody destination = createPartFromString(String.valueOf(req.destination));
        RequestBody sample_type = createPartFromString(String.valueOf(req.sample_type));
        RequestBody disease = createPartFromString(String.valueOf(req.disease));
        RequestBody transporter = createPartFromString(String.valueOf(req.transporter));
        RequestBody notes = createPartFromString(req.notes);

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("initialSampleDate", initialSampleDate);
        map.put("finalDestinationDate", finalDestinationDate);
        map.put("facility_code", facility_code);
        map.put("barcode", barcode);
        map.put("destination", destination);
        map.put("sample_type", sample_type);
        map.put("disease", disease);
        map.put("transporter", transporter);
        map.put("notes", notes);

        Call<RegisterSampleRes> call = service.registerSample(map, body);
        call.enqueue(new Callback<RegisterSampleRes>() {
            @Override
            public void onResponse(Call<RegisterSampleRes> call, Response<RegisterSampleRes> response) {

                handleServerResponse(response);

            }

            @Override
            public void onFailure(Call<RegisterSampleRes> call, Throwable t) {

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

    private void handleServerResponse(Response<RegisterSampleRes> response) {

        if(response.code() == NetworkStatusCodes.CODE_SUCCESS){

            responseListener.serverResponseRegisterSampleSuccess(response.body());

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

    public interface ServerResponseRegisterSampleListener {

        void serverResponseError(String error);
        void serverResponseRegisterSampleSuccess(RegisterSampleRes response);

    }

}
