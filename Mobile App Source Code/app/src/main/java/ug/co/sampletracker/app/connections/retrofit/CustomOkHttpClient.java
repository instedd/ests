package ug.co.sampletracker.app.connections.retrofit;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import ug.co.sampletracker.app.utils.security.SecurityHelper;

public class CustomOkHttpClient extends OkHttpClient {

    private static OkHttpClient okHttpClient = null;

    private CustomOkHttpClient() {
    }

    public static OkHttpClient newInstance(){

        if(okHttpClient != null){
            return okHttpClient;
        }

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        JavaNetCookieJar jncj = new JavaNetCookieJar(cookieManager);

        OkHttpClient.Builder httpClient = SecurityHelper.getUnsafeOkHttpClient();//new OkHttpClient.Builder();
        httpClient.addInterceptor(logging).connectTimeout(180/*60*/, TimeUnit.SECONDS).readTimeout(180/*60*/, TimeUnit.SECONDS);
        httpClient.cookieJar(jncj);

        okHttpClient = httpClient.build();

        return okHttpClient;

    }
}
