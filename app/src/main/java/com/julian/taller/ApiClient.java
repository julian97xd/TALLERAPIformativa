package com.julian.taller;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final int CONNECTION_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 30;
    private static Retrofit retrofit = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = builder
                    .build();
        }
        return retrofit;
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl("https://www.googleapis.com/books/v1/")
                    .client(new okhttp3.OkHttpClient.Builder()
                            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build())
                    .addConverterFactory(GsonConverterFactory.create());



    public static class ErrorInterceptor implements Interceptor {
        Response response = null;
        int tryCount = 0;
        int maxLimit = 3;
        int waitThreshold = 5000;

        @Override
        public Response intercept(Chain chain) {

            Request request = chain.request();
            response = sendRequest(chain, request);
            while (response == null && tryCount < maxLimit) {
                Log.d("intercept", "Request failed - " + tryCount);
                tryCount++;
                try {
                    Thread.sleep(waitThreshold); // force wait the network thread for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                response = sendRequest(chain, request);
            }
            return response;
        }

        private Response sendRequest(Interceptor.Chain chain, Request request) {
            try {
                response = chain.proceed(request);
                if (!response.isSuccessful())
                    return null;
                else
                    return response;
            } catch (IOException e) {
                return null;
            }
        }
    }
}
