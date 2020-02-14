package com.mandy.satyam.webAPI;

import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.addressActivity.response.GetAddress;
import com.mandy.satyam.addressActivity.response.UpdateAddress;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.login.model.ClearCart;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.myCart.response.GetCartProducts;
import com.mandy.satyam.myCart.response.RemoveCartItem;
import com.mandy.satyam.myCart.response.UpdateCart;
import com.mandy.satyam.myProfile.response.GetProfile;
import com.mandy.satyam.productDetails.response.AddToCart;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productList.response.SubCategory;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("wp-json/cocart/v1/clear")
    Call<ClearCart> clearCart();

    @POST("wp-json/os/v1/add_to_cart")
    Call<AddToCart> addtoCart(
            @Query("product_id") String consumer_key,
            @Query("quantity") String consumer_secret,
            @Query("variation_id") String product_id,
            @Query("token") String quantity
    );

    @POST("wp-json/os/v1/cart")
    Call<GetCartProducts> getCartProducts(
            @Query("token") String token
    );


    @POST("wp-json/os/v1/update_cart_item")
    Call<UpdateCart> updateCart(
            @Query("cart_id") String cart_id,
            @Query("token") String token,
            @Query("quantity") String quantity
    );

    @POST("wp-json/os/v1/remove_cart_item")
    Call<RemoveCartItem> removeCartItem(
            @Query("cart_id") String cart_id,
            @Query("user_id") String user_id,
            @Query("token") String token
    );

    @POST("wp-json/os/v1/userdetails")
    Call<GetProfile> getProfile(
            @Query("token") String token
    );

    @GET("wp-json/wc/v3/customers/{input}")
    Call<GetAddress> getAddress(
            @Path("input") String id,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret
    );

    @PUT("wp-json/wc/v3/customers/{input}")
    Call<UpdateAddress> updateAddress(
            @Path("input") String id,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("billing[first_name]") String first_name,
            @Query("billing[address_1]") String address1,
            @Query("billing[address_2]") String address2,
            @Query("billing[city]") String city,
            @Query("billing[postcode]") String postcode,
            @Query("billing[state]") String state,
            @Query("billing[phone]") String phone


    );
}
