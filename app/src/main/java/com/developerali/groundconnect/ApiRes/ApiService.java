package com.developerali.groundconnect.ApiRes;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {


    @Multipart
    @POST("uploadFile") // Your PHP file location
    Call<ApiResponse> uploadFile(
            @Query("token") String token,
            @Part MultipartBody.Part file
    );


}
