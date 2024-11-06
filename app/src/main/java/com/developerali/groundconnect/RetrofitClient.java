package com.developerali.groundconnect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://groundreport.news/simple-api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {

            List<Protocol> protocols = new ArrayList<Protocol>()
            {{
                add(Protocol.HTTP_1_1);
            }};

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(300L, TimeUnit.SECONDS)
                    .writeTimeout  (300L, TimeUnit.SECONDS)
                    .readTimeout   (300L, TimeUnit.SECONDS)
                    .protocols(protocols)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
