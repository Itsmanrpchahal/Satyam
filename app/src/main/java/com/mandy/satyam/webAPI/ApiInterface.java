package com.mandy.satyam.webAPI;

import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("logincheck")
    Call<LoginCheck> loginCheck(
            @Query("email_or_phone") String email_or_phone,
            @Query("login_type") String login_type
    );

    @POST("login")
    Call<Login> login(
            @Query("email_or_phone") String email_or_phone,
            @Query("login_type") String login_type,
            @Query("password") String password

    );
}
