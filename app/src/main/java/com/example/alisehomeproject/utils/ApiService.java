package com.example.alisehomeproject.utils;

import com.example.alisehomeproject.Pojo.OtpRequest;
import com.example.alisehomeproject.Pojo.Otpsubmit;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("phone_number_login")
    Call<JsonObject> getOtpStatus(@Header("Cookie")String Cookie, @Body OtpRequest otpRequest);
    @POST("verify_otp")
    Call<JsonObject> getSubmitOtpStatus(@Header("Cookie")String Cookie, @Body Otpsubmit otpsubmit);
    @GET("test_profile_list")
    Call<JsonObject> getSubmitOtpStatus(@Header("Cookie")String Cookie, @Header("Authorization")String Authorization);
}
