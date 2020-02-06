package com.mandy.satyam.controller;

import android.util.Log;

import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.login.model.ClearCart;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productList.response.SubCategory;
import com.mandy.satyam.webAPI.WebAPI;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class Controller {

    public WebAPI webAPI;
    public LoginCheck loginCheck;
    public Login login;
    public HomePage homePage;
    public Keys keys;
    public RelatedPrducts relatedPrducts;
    public ProductDetail productDetail;
    public ProductSubCategories subCategory;
    public ClearCart clearCart;

    //logincheck
    public Controller(LoginCheck loginCheck1) {
        loginCheck = loginCheck1;
        webAPI = new WebAPI();
    }


    //logincheck login
    public Controller(LoginCheck loginCheck1,Login login1,ClearCart clearCart1) {
        loginCheck = loginCheck1;
        clearCart = clearCart1;
        webAPI = new WebAPI();
        login = login1;
    }

    //HomePage
    public Controller(HomePage homePage1)
    {
        homePage = homePage1;
        webAPI = new WebAPI();
    }

    //related
    public Controller(RelatedPrducts relatedPrducts1,ProductSubCategories subCategory1)
    {
        relatedPrducts = relatedPrducts1;
        subCategory = subCategory1;
        webAPI = new WebAPI();
    }

    //productDetail
    public Controller(ProductDetail productDetail1,RelatedPrducts relatedPrducts1)
    {
        productDetail = productDetail1;
        relatedPrducts = relatedPrducts1;
        webAPI = new WebAPI();
    }
    //Keys
    public Controller(Keys keys1)
    {
        keys = keys1;
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

    public void setKeys(String token)
    {
        webAPI.getApi().keys(token).enqueue(new Callback<KeysResponse>() {
            @Override
            public void onResponse(Call<KeysResponse> call, Response<KeysResponse> response) {
                if (response!=null)
                {
                    Response<KeysResponse> keysResponseResponse = response;
                    keys.onSuccess(keysResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<KeysResponse> call, Throwable t) {
            keys.onError(t.getMessage());
            }
        });
    }

    public void setRelatedPrducts(String cosumerKey,String consumerSecret,String category,String page)
    {
        webAPI.getApi().homeResponse(cosumerKey,consumerSecret,category,page).enqueue(new Callback<Categoriesroducts>() {
            @Override
            public void onResponse(Call<Categoriesroducts> call, Response<Categoriesroducts> response) {
                if (response!=null)
                {
                    Response<Categoriesroducts> homePageResponseResponse = response;
                    relatedPrducts.onSucessRelated(homePageResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<Categoriesroducts> call, Throwable t) {
                relatedPrducts.onError(t.getMessage());
            }
        });
    }

    public void setProductDetail(String id,String cosumerKey,String consumerSecret)
    {
        webAPI.getApi().productDetail(id,cosumerKey,consumerSecret).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response!=null)
                {
                    Response<ProductDetailResponse> productDetailResponseResponse = response;
                    productDetail.onSuccessProductDetail(productDetailResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<ProductDetailResponse> call, Throwable t) {
                productDetail.onError(t.getMessage());
            }
        });
    }


    public void setSubCategory(String category_id)
    {
        webAPI.getApi().subcategories(category_id).enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                if (response!=null)
                {
                    Response<SubCategory> subCategoryResponse = response;
                    subCategory.onSuccessSubcate(subCategoryResponse);
                }
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {
                subCategory.onError(t.getMessage());
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

    public interface Keys{
        void onSuccess(Response<KeysResponse> keysResponseResponse);
        void onError(String error);
    }

    public interface RelatedPrducts{
        void onSucessRelated(Response<Categoriesroducts> homePageResponseResponse);
        void onError(String error);
    }

    public interface ProductDetail{
        void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse);
        void onError(String error);
    };

    public interface ProductSubCategories{
        void onSuccessSubcate(Response<SubCategory> subCategoryResponse);
        void onError(String error);
    }

    public interface ClearCart{
        void onSuccessClearCart(Response<com.mandy.satyam.login.model.ClearCart> response);
        void onError(String error);
    }
}
