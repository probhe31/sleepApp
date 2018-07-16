package com.probe31.probe.sleepyapp.WEB_SERVICE;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sleepapi.cfapps.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        return retrofit;
    }

    public static Retrofit getRetrofitInstance(final String authToken) {


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        clientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        clientBuilder.readTimeout(60, TimeUnit.SECONDS);
        clientBuilder.writeTimeout(60, TimeUnit.SECONDS);

        final String token = "Token " + authToken;

        Interceptor headerAuthorizationInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                okhttp3.Request request = chain.request();//Token 4358a340d8d7aa32d537bdd5c4e1378ea3a9bc58
                Headers headers = request.headers().newBuilder().add("Authorization", token).build();
                request = request.newBuilder().headers(headers).build();
                return chain.proceed(request);
            }
        };

        clientBuilder.addInterceptor(headerAuthorizationInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sleepapi.cfapps.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();


        return retrofit;
    }

}
