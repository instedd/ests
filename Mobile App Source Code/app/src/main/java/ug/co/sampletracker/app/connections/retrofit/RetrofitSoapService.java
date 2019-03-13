package ug.co.sampletracker.app.connections.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import ug.co.sampletracker.app.models.responses.SmsResponse;

/**
 * Created by Timothy Kasaga for Leontymo Developers on 9/10/2018.
 */
public interface RetrofitSoapService {

    @GET("/xml")
    Call<SmsResponse> getProducts();

}
