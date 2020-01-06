package com.mandy.satyam.retrofit;

import com.mandy.satyam.commentActivity.CommentsApi;
import com.mandy.satyam.homeFragment.apis.BannerApi;
import com.mandy.satyam.homeFragment.apis.CategoryApi;
import com.mandy.satyam.GetMeesageApi;
import com.mandy.satyam.myOrderList.OrderListApi;
import com.mandy.satyam.myOrderList.myorderdetails.OrderDetailsApi;
import com.mandy.satyam.productDetails.apis.GetAddToCart;
import com.mandy.satyam.payment.PaymentProductApi;
import com.mandy.satyam.productDetails.apis.GetProductDetailsApi;
import com.mandy.satyam.addressActivity.GetAddressApi;
import com.mandy.satyam.productList.GetProductList;
import com.mandy.satyam.home2.GetSubCategoryApi;
import com.mandy.satyam.login.LoginApi;
import com.mandy.satyam.myProfile.ProfileApi;
import com.mandy.satyam.searchActivity.SearchListApi;
import com.mandy.satyam.signup.SignUpApi;
import com.mandy.satyam.signup.VerificationOtpApi;
import com.mandy.satyam.myCart.exploremore.GetExploreMoreData;
import com.mandy.satyam.myCart.GetCartDataApi;
import com.mandy.satyam.myCart.topitems.GetTopDataApi;
import com.mandy.satyam.termsandcondition.TermsConditionApi;

import org.json.JSONArray;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    // Sign up Api
    @POST("register")
    Call<SignUpApi> signupApi(
            @Query("name") String Name,
            @Query("phone") String phone,
            @Query("password") String password
    );

    // sign up otp verification api
    @POST("user_verify")
    Call<VerificationOtpApi> userVerification(
            @Query("otp") String otp,
            @Query("phone") String phone,
            @Query("id") String id
    );

    //Login Api
    @POST("login")
    Call<LoginApi> loginApi(
            @Query("phone") String phone,
            @Query("password") String password
    );

    //get the otp for forgot password
    @POST("forget_password")
    Call<GetMeesageApi> getOtp(
            @Query("phone") String phone
    );

    @POST("resend_otp")
    Call<GetMeesageApi> sendOtp(
            @Query("otp") String otp,
            @Query("phone") String phone
    );

    //create the new password
    @POST("change_password")
    Call<GetMeesageApi> resetPassword(
            @Query("password") String password,
            @Query("phone") String phone
    );


    //get category
    @POST("categories")
    Call<CategoryApi> getCategory();

    //get the new Arrival product
    @POST("new_arrivals")
    Call<GetProductList> newArrivalsApi();

    //get the Discounted product
    @POST("discounted_products")
    Call<GetProductList> getDiscountedapi();


    //sele banners
    @POST("sales_banner")
    Call<BannerApi> getBanner();


    //get the profile details
    @POST("get_profile")
    Call<ProfileApi> getProfile(
            @Header("Authorization") String authorization
    );

    // upload the profile images
    @Multipart
    @POST("update_image")
    Call<ProfileApi> getProfileImage(
            @Header("Authorization") String authorization,
            @Part MultipartBody.Part part
    );

    // update the profile data
    @POST("edit_profile")
    Call<ProfileApi> getProfileUpdate(
            @Header("Authorization") String authorization,
            @Query("email") String Email,
            @Query("name") String Name
    );

    //get the sub category
    @POST("get_cat_products")
    Call<GetSubCategoryApi> getSubCategory(
            @Header("Authorization") String authorization,
            @Query("cat_id") String Id
    );

    //get the product list
    @POST("get_products")
    Call<GetProductList> getProductList(
            @Header("Authorization") String authorization,
            @Query("product_cat_id") String Id
    );

    //get the product details
    @POST("get_single_product")
    Call<GetProductDetailsApi> getProductDetails(
            @Header("Authorization") String authorization,
            @Query("id") String Id
    );

    //get the related product list
    @POST("get_related_products")
    Call<GetProductList> getRelatedList(
            @Header("Authorization") String authorization,
            @Query("product_cat_id") String Id
    );

    // add data into cart
    @POST("add_to_cart")
    Call<GetAddToCart> addToCart(
            @Header("Authorization") String authorization,
            @Query("product_id") String Id,
            @Query("product_color") String Color,
            @Query("product_size") String Size
    );

    //get the cart number
    @POST("get_cart_number")
    Call<GetAddToCart> getCartNumber(
            @Header("Authorization") String authorization
    );

    //get the cart data
    @POST("get_cart_products")
    Call<GetCartDataApi> getCartData(
            @Header("Authorization") String authorization
    );


    //add more quaninty
    @POST("add_product_quatity")
    Call<GetMeesageApi> addQuantinty(
            @Header("Authorization") String authorization,
            @Query("cart_id") String Id,
            @Query("quantity_num") String number
    );

    //delete from  my cart
    @POST("delete_from_cart")
    Call<GetMeesageApi> deleteProduct(
            @Header("Authorization") String authorization,
            @Query("cart_product_id") String Id
    );

    //get explore more data  from apis
    @POST("get_explore_more")
    Call<GetExploreMoreData> getMorePrduct(
            @Header("Authorization") String authorization
    );

    //delete from  my cart
    @POST("get_top_pickup")
    Call<GetTopDataApi> getTopData(
            @Header("Authorization") String authorization
    );

    // add address
    @POST("add_address")
    Call<GetMeesageApi> addAddress(
            @Header("Authorization") String authorization,
            @Query("fullname") String fullname,
            @Query("phone") String phone,
            @Query("postcode") String postcode,
            @Query("city") String city,
            @Query("state") String state,
            @Query("address") String address,
            @Query("landmark") String landmark

    );


    //get the Addresss list
    @POST("get_user_addresses")
    Call<GetAddressApi> getAddressList(
            @Header("Authorization") String authorization
    );


    //get the terms and condition list
    @POST("terms_and_policy")
    Call<TermsConditionApi> getTerms(
            @Header("Authorization") String authorization
    );


    //get the Buy items list
    @POST("buy_products")
    Call<PaymentProductApi> getBuyItemsList(
            @Header("Authorization") String authorization,
            @Query("product_id") String id,
            @Query("color") String color,
            @Query("size") String size,
            @Query("quantity")String quantity
    );


    //get the Buy items list
    @POST("create_checksum")
    Call<ResponseBody> getCheckSomeCode(
            @Header("Authorization") String authorization,
            @Query("MID") String MID,
            @Query("ORDER_ID") String ORDER_ID,
            @Query("CUST_ID") String CUST_ID,
            @Query("INDUSTRY_TYPE_ID") String INDUSTRY_TYPE_ID,
            @Query("CHANNEL_ID") String CHANNEL_ID,
            @Query("TXN_AMOUNT") String TXN_AMOUNT,
            @Query("WEBSITE") String WEBSITE,
            @Query("CALLBACK_URL") String CALLBACK_URL
    );

    //save the order list in android
    @POST("create_order")
    Call<GetMeesageApi> saveorderlist(
            @Header("Authorization") String authorization,
            @Query("order_id") String orderId,
            @Query("address") String address,
            @Query("total_price") String total_price,
            @Query("transaction_id") String transaction_id,
            @Query("order_details") JSONArray jsonObject

    );

    //Clear the cart
    @POST("empty_cart")
    Call<GetMeesageApi> clearCart(
            @Header("Authorization") String authorization
    );

    // get the banner product list
    @POST("get_sales_banner_products")
    Call<GetProductList> getBannerProductList(
            @Query("product_cat_ids") String catIds
    );

    // get the Best sell product list
    @POST("best_selling_products")
    Call<GetProductList> getBestSell();


    // get order list
    @POST("get_order_list")
    Call<OrderListApi> getOrderList(
            @Header("Authorization") String authorization
    );

    //get my orderlist Details
    @POST("deliver_order_details")
    Call<OrderDetailsApi> getOrderDetails(
            @Header("Authorization") String authorization,
            @Query("order_id") String orderId
    );


    //get my orderlist Details
    @POST("save_rating_reviews")
    Call<GetMeesageApi> rateNow(
            @Header("Authorization") String authorization,
            @Query("product_id") String productId,
            @Query("rating") String rating,
            @Query("comment") String comment
    );

    //get comments list
    @POST("get_products_reviews")
    Call<CommentsApi> getComments(
            @Header("Authorization") String authorization,
            @Query("product_id") String productId
    );


    //get support
    @POST("send_email_query")
    Call<GetMeesageApi> support(
            @Header("Authorization") String authorization,
            @Query("content") String content
    );

    //get support
    @POST("getAllSearchCats")
    Call<SearchListApi> searchList(
            @Header("Authorization") String authorization
    );

    @POST("getAllSearchProducts")
    Call<GetProductList> getAllsearch(
            @Header("Authorization") String token,
            @Query("product_cat_id") String id
    );

}
