package com.mandy.satyam.controller;

import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.webAPI.WebAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Controller {

    public WebAPI webAPI;
    public LoginCheck loginCheck;
    public Login login;
    public HomePage homePage;


    //logincheck
    public Controller(LoginCheck loginCheck1) {
        loginCheck = loginCheck1;
        webAPI = new WebAPI();
    }

    //logincheck login
    public Controller(LoginCheck loginCheck1,Login login1) {
        loginCheck = loginCheck1;
        webAPI = new WebAPI();
        login = login1;
    }

    //HomePage
    public Controller(HomePage homePage1)
    {
        homePage = homePage1;
        webAPI = new WebAPI();
    }

    //ToDo: Rest API's
    public void setLoginCheck(String email_phone, String type) {
        webAPI.getApi().loginCheck(email_phone, type).enqueue(new Callback<com.mandy.satyam.login.model.LoginCheck>() {
            @Override
            public void onResponse(Call<com.mandy.satyam.login.model.LoginCheck> call, Response<com.mandy.satyam.login.model.LoginCheck> response) {
                if (response != null) {
                    Response<com.mandy.satyam.login.model.LoginCheck> loginCheckResponse = response;
                    loginCheck.onSuccessLoginCheck(loginCheckResponse);
                }
            }

            @Override
            public void onFailure(Call<com.mandy.satyam.login.model.LoginCheck> call, Throwable t) {
                loginCheck.onError(t.getMessage());
            }
        });
    }


    public void setLogin(String email,String type,String passsword)
    {
        webAPI.getApi().login(email,type,passsword).enqueue(new Callback<com.mandy.satyam.login.model.Login>() {
            @Override
            public void onResponse(Call<com.mandy.satyam.login.model.Login> call, Response<com.mandy.satyam.login.model.Login> response) {
                if (response!=null)
                {
                    Response<com.mandy.satyam.login.model.Login> loginResponse = response;
                    login.onsetLogin(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<com.mandy.satyam.login.model.Login> call, Throwable t) {
            login.onError(t.getMessage());
            }
        });
    }

    public void setHomePage()
    {
        webAPI.getApi().homepage().enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                if (response!=null)
                {
                    Response<HomePageResponse> homePageResponseResponse = response;
                    homePage.onSucessHome(homePageResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<HomePageResponse> call, Throwable t) {
                homePage.onError(t.getMessage());
            }
        });
    }

    public interface LoginCheck {
        void onSuccessLoginCheck(Response<com.mandy.satyam.login.model.LoginCheck> loginCheckResponse);
        void onError(String error);
    }

    public interface Login{
        void onsetLogin(Response<com.mandy.satyam.login.model.Login> loginResponse);
        void onError(String error);
    }

    public interface HomePage{
        void onSucessHome(Response<HomePageResponse> homePageResponseResponse);
        void onError(String error);
    }
}
