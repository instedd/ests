package ug.co.sampletracker.app.connections.retrofit;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by timothy kasaga on 4/2/2017.
 */

public class LocalRetrofitApi {

    private Retrofit retrofit1;

    public LocalRetrofitApi(String baseServerUrl) {

        this.retrofit1 = new Retrofit.Builder()
                .client(CustomOkHttpClient.newInstance())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseServerUrl)
                .build();
    }

    private Retrofit getRetrofit1() {
        return retrofit1;
    }

    public RetrofitService getRetrofitService(){
        return this.getRetrofit1().create(RetrofitService.class);
    }


    @NonNull
    public static RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }


    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, File file, Uri fileUri, Context context) {

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(context.getContentResolver().getType(fileUri)),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

}
