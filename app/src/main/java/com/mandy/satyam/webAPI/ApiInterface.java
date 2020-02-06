package com.mandy.satyam.webAPI;

import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productList.response.SubCategory;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("wp-json/os/v1/logincheck")
    Call<LoginCheck> loginCheck(
            @Query("email_or_phone") String email_or_phone,
            @Query("login_type") String login_type
    );

    @POST("wp-json/os/v1/login")
    Call<Login> login(
            @Query("email_or_phone") String email_or_phone,
            @Query("login_type") String login_type,
            @Query("password") String password
    );

    @POST("wp-json/os/v1/homepage")
    Call<HomePageResponse> homepage(

    );

    @POST("wp-json/os/v1/woo_keys")
    Call<KeysResponse> keys
            (
                    @Query("token") String token
            );

    @GET("wp-json/wc/v3/products")
    Call<Categoriesroducts> homeResponse(
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("category") String category,
            @Query("page") String page
    );

    @GET("wp-json/wc/v3/products/{input}")
    Call<ProductDetailResponse> productDetail(
            @Path("input") String id,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret

    );

    @POST("wp-json/os/v1/sub_categories")
    Call<SubCategory> subcategories(
            @Query("category_id") String category_id
    );
}
