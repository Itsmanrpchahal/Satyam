package com.mandy.satyam.webAPI;

import com.mandy.satyam.KeysResponse;
import com.mandy.satyam.addressActivity.response.GetAddress;
import com.mandy.satyam.addressActivity.response.GetCities;
import com.mandy.satyam.addressActivity.response.GetZones;
import com.mandy.satyam.addressActivity.response.UpdateAddress;
import com.mandy.satyam.filterScreen.response.FilterResponse;
import com.mandy.satyam.homeFragment.response.Categoriesroducts;
import com.mandy.satyam.homeFragment.response.HomePageResponse;
import com.mandy.satyam.login.model.ClearCart;
import com.mandy.satyam.login.model.Login;
import com.mandy.satyam.login.model.LoginCheck;
import com.mandy.satyam.login.model.SocialLoginResponse;
import com.mandy.satyam.myCart.response.GetCartProducts;
import com.mandy.satyam.myCart.response.RemoveCartItem;
import com.mandy.satyam.myCart.response.UpdateCart;
import com.mandy.satyam.myOrderList.response.GetAllOrders;
import com.mandy.satyam.myOrderList.response.GetOrderDetail;
import com.mandy.satyam.myProfile.response.GetProfile;
import com.mandy.satyam.myProfile.response.UpdateProfile;
import com.mandy.satyam.placeorder.CreateOrder;
import com.mandy.satyam.placeorder.CreateOrderPojo;
import com.mandy.satyam.productDetails.response.AddToCart;
import com.mandy.satyam.productDetails.response.ProductDetailResponse;
import com.mandy.satyam.productDetails.response.VariationResponse;
import com.mandy.satyam.productList.response.GetSearchProductsResponse;
import com.mandy.satyam.productList.response.SubCategory;

import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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


    @POST("wp-json/os/v1/social_login")
    Call<SocialLoginResponse> socialLogin(
            @Query("token") String token,
            @Query("type") String type,
            @Query("email") String email,
            @Query("image") String image,
            @Query("first_name") String first_name,
            @Query("last_name") String last_name
    );

    @POST("wp-json/os/v1/homepage")
    Call<HomePageResponse> homepage(
        @Query("token") String token
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
            @Query("page") String page,
            @Query("per_page") int per_page,
            @Query("amr_slug") String amr_slug
    );

    @GET("wp-json/wc/v3/products/{input}")
    Call<ProductDetailResponse> productDetail(
            @Path("input") String id,
            @Query("token") String token,
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
            @Query("amr_slug") String amr_slug,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret
    );

    @PUT("wp-json/wc/v3/customers/{input}")
    Call<UpdateAddress> updateAddress(
            @Path("input") String id,
            @Query("amr_slug") String amr_slug,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("billing[first_name]") String first_name,
            @Query("billing[last_name]") String last_name,
            @Query("billing[address_1]") String address1,
            @Query("billing[address_2]") String address2,
            @Query("billing[city]") String city,
            @Query("billing[postcode]") String postcode,
            @Query("billing[state]") String state,
            @Query("billing[phone]") String phone,
            @Query("billing[email]") String email,
            @Query("address_type") String address_type,
            @Query("address_type_text") String address_type_text,
            @Query("alternate_phone") String alternate_phone,
            @Query("ward_number") String ward_number,
            @Query("billing[country]") String country
    );

    @Multipart
    @POST("wp-json/os/v1/update_user")
    Call<UpdateProfile> updateProfile(
            @Query("token") String token,
            @Query("first_name") String first_name,
            @Query("last_name") String last_name,
            @Query("email") String email,
            @Query("password") String password,
            @Part MultipartBody.Part userImage
            );

    @Multipart
    @POST("wp-json/os/v1/update_user")
    Call<UpdateProfile> updateProfile1(
            @Query("token") String token,
            @Query("first_name") String first_name,
            @Query("last_name") String last_name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Part MultipartBody.Part userImage
    );



    @POST("wp-json/os/v1/create_order")
    Call<CreateOrder> createOrder(
            @Query("payment_method") String payment_method,
            @Query("payment_method_title") String payment_method_title,
            @Query("set_paid") boolean set_paid,
            @Query("billing[first_name]") String first_name,
            @Query("billing[last_name]") String last_name,
            @Query("billing[address_1]") String address_1,
            @Query("billing[address_2]") String address_2,
            @Query("billing[city]") String city,
            @Query("billing[state]") String state,
            @Query("billing[postcode]") String postcode,
            @Query("billing[country]") String country,
            @Query("billing[email]") String email,
            @Query("billing[phone]") String phone,
            @Query("shipping[first_name]") String Sfirst_name,
            @Query("shipping[last_name]") String Slast_name,
            @Query("shipping[address_1]")  String Saddress_1,
            @Query("shipping[address_2]")  String Saddress_2,
            @Query("shipping[city]") String Scity,
            @Query("shipping[state]") String Sstate,
            @Query("shipping[postcode]") String Spostcode,
            @Query("shipping[country]") String Scountry,
            @Query("line_items")String line_items,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("customer_id") String customer_id,
            @Query("amr_slug") String amr_slug

    );

    @POST("wp-json/wc/v3/orders")
    Call<CreateOrder> createOrder1(

        @Body CreateOrderPojo createOrderPojo,
        @Query("consumer_key") String consumer_key,
        @Query("consumer_secret") String consumer_secret,
        @Query("customer_id") String customer_id,
        @Query("amr_slug") String amr_slug
    );

    @GET("wp-json/wc/v3/orders/")
    Call<GetAllOrders> getAllOrders(
           @Query("customer") String customer,
           @Query("consumer_key") String consumer_key,
           @Query("consumer_secret") String consumer_secret,
           @Query("amr_slug")String amr_slug
    );

    @GET("wp-json/wc/v3/products/")
    Call<FilterResponse> filterProducts(
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("category") String category,
            @Query("min_price") String min_price,
            @Query("max_price") String max_price,
            @Query("search") String search,
            @Query("page") String page,
            @Query("per_page") int per_page
    );

    @GET("wp-json/wc/v3/orders/{input}")
    Call<GetOrderDetail> getOrderDetail(
            @Path("input") String id,
            @Query("consumer_key") String consumer_key,
            @Query("consumer_secret") String consumer_secret,
            @Query("amr_slug") String amr_slug
    );

    @POST("wp-json/os/v1/get_variation")
    Call<VariationResponse> variations(
            @Query("product_id") String product_id,
           @Query("variations") String variations
    );

    @POST("wp-json/os/v1/get_search")
    Call<GetSearchProductsResponse> getSeacchProduct(
            @Query("search") String search
    );

    @POST("wp-json/os/v1/get_zones")
    Call<GetZones> getZones(

    );


    @POST("wp-json/os/v1/get_cities")
    Call<GetCities> getCities(
      @Query("state_id") String state_id
    );
}
