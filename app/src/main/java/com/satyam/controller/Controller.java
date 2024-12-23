package com.satyam.controller;

import com.satyam.KeysResponse;
import com.satyam.addressActivity.response.GetZones;
import com.satyam.filterScreen.response.FilterResponse;
import com.satyam.homeFragment.response.Categoriesroducts;
import com.satyam.homeFragment.response.HomePageResponse;
import com.satyam.login.model.SocialLoginResponse;
import com.satyam.myOrderList.response.GetOrderDetail;
import com.satyam.placeorder.CreateOrder;
import com.satyam.placeorder.CreateOrderPojo;
import com.satyam.productDetails.response.ProductDetailResponse;
import com.satyam.productDetails.response.VariationResponse;
import com.satyam.productList.response.GetSearchProductsResponse;
import com.satyam.productList.response.SubCategory;
import com.satyam.webAPI.WebAPI;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public AddToCart addToCart;
    public GetCartProducts getCartProducts;
    public UpdateCart updateCart;
    public UpdateProfile1 updateProfile1;
    public RemoveCartItem removeCartItem;
    public GetProfile getProfile;
    public GetAddress getAddress;
    public UpdateAddress updateAddress;
    public UpdateProfile updateProfile;
    public PlaceOrder placeOrder;
    public PlaceOrder1 placeOrder1_;
    public GetAllOrders getAllOrders;
    public GetFilterProducts getFilterProducts;
    public GetOrderDetails getOrderDetails;
    public GetVariations getVariations;
    public GetSearchProducts getSearchProducts;
    public GetSerchProducts getSerchProducts;
    public GetZone getZone;
    public GetCities getCities;
    public SocailLogin socailLogin;
    public PrivacyTD privacyTD;


    //logincheck
    public Controller(LoginCheck loginCheck1) {
        loginCheck = loginCheck1;
        webAPI = new WebAPI();
    }

    public Controller(UpdateProfile1 updateProfile2)
    {
        updateProfile1 = updateProfile2;
        webAPI = new WebAPI();
    }

    //logincheck login
    public Controller(LoginCheck loginCheck1, Login login1, ClearCart clearCart1,SocailLogin socailLogin1) {
        loginCheck = loginCheck1;
        clearCart = clearCart1;
        webAPI = new WebAPI();
        socailLogin = socailLogin1;
        login = login1;
    }

    //HomePage
    public Controller(HomePage homePage1) {
        homePage = homePage1;
        webAPI = new WebAPI();
    }

    //related
    public Controller(RelatedPrducts relatedPrducts1, ProductSubCategories subCategory1, GetFilterProducts getFilterProducts1,GetSerchProducts getSerchProducts1) {
        relatedPrducts = relatedPrducts1;
        subCategory = subCategory1;
        getFilterProducts = getFilterProducts1;
        getSerchProducts = getSerchProducts1;
        webAPI = new WebAPI();
    }

    //productDetail
    public Controller(ProductDetail productDetail1, RelatedPrducts relatedPrducts1, AddToCart addToCart1,GetVariations getVariations1) {
        productDetail = productDetail1;
        relatedPrducts = relatedPrducts1;
        addToCart = addToCart1;
        getVariations = getVariations1;
        webAPI = new WebAPI();
    }

    public Controller(Keys keys1)
    {
        keys = keys1;
        webAPI = new WebAPI();
    }

    //Keys
    public Controller(Keys keys1,HomePage homePage1,GetSerchProducts getSerchProducts1) {
        keys = keys1;
        homePage = homePage1;
        getSerchProducts = getSerchProducts1;
        webAPI = new WebAPI();
    }

    //Clear Cart
    public Controller(ClearCart clearCart1) {
        clearCart = clearCart1;
        webAPI = new WebAPI();
    }

    //add to cart
    public Controller(AddToCart addToCart1) {
        addToCart = addToCart1;
        webAPI = new WebAPI();
    }

    //getAllOrder
    public Controller(GetAllOrders getAllOrders1) {
        getAllOrders = getAllOrders1;
        webAPI = new WebAPI();
    }

    //getCartProducts
    public Controller(GetCartProducts getCartProducts1, UpdateCart updateCart1, RemoveCartItem removeCartItem1) {
        getCartProducts = getCartProducts1;
        updateCart = updateCart1;
        removeCartItem = removeCartItem1;
        webAPI = new WebAPI();
    }

    public Controller(GetProfile getProfile1, UpdateProfile updateProfile1) {
        getProfile = getProfile1;
        updateProfile = updateProfile1;
        webAPI = new WebAPI();
    }

    public Controller(GetAddress getAddress1, UpdateAddress updateAddress1, PlaceOrder placeOrder1,PlaceOrder1 placeOrder1__,GetZone getZone1,GetCities getCities1) {
        getAddress = getAddress1;
        updateAddress = updateAddress1;
        placeOrder = placeOrder1;
        placeOrder1_ = placeOrder1__;
        getZone = getZone1;
        getCities = getCities1;
        webAPI = new WebAPI();
    }

    public Controller(ProductSubCategories subCategory1) {
        subCategory = subCategory1;
        webAPI = new WebAPI();
    }

    //getOrderDetail
    public Controller(GetOrderDetails getOrderDetails1)
    {
        getOrderDetails = getOrderDetails1;
        webAPI = new WebAPI();
    }


    //privacyTD
    public Controller(PrivacyTD privacyTD1)
    {
        privacyTD = privacyTD1;
        webAPI = new WebAPI() ;
    }

    //ToDo: Rest API's
    public void setLoginCheck(String email_phone, String type) {
        webAPI.getApi().loginCheck(email_phone, type).enqueue(new Callback<com.satyam.login.model.LoginCheck>() {
            @Override
            public void onResponse(Call<com.satyam.login.model.LoginCheck> call, Response<com.satyam.login.model.LoginCheck> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.login.model.LoginCheck> loginCheckResponse = response;
                    loginCheck.onSuccessLoginCheck(loginCheckResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.login.model.LoginCheck> call, Throwable t) {
                loginCheck.onError(t.getMessage());
            }
        });
    }


    public void setLogin(String email, String type, String passsword) {
        webAPI.getApi().login(email, type, passsword).enqueue(new Callback<com.satyam.login.model.Login>() {
            @Override
            public void onResponse(Call<com.satyam.login.model.Login> call, Response<com.satyam.login.model.Login> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.login.model.Login> loginResponse = response;
                    login.onsetLogin(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.login.model.Login> call, Throwable t) {
                login.onError(t.getMessage());
            }
        });
    }


    public void setSocailLogin(String token,String type,String email,String image,String first_name,String last_name)
    {
        webAPI.getApi().socialLogin(token, type, email, image, first_name, last_name).enqueue(new Callback<SocialLoginResponse>() {
            @Override
            public void onResponse(Call<SocialLoginResponse> call, Response<SocialLoginResponse> response) {
                if(response.isSuccessful())
                {
                    Response<SocialLoginResponse> socailLoginResponse = response;
                    socailLogin.onSuccessSocailLogin(socailLoginResponse);
                }
            }

            @Override
            public void onFailure(Call<SocialLoginResponse> call, Throwable t) {
                    socailLogin.onError(t.getMessage());
            }
        });
    }

    public void setHomePage(String token) {
        webAPI.getApi().homepage(token).enqueue(new Callback<HomePageResponse>() {
            @Override
            public void onResponse(Call<HomePageResponse> call, Response<HomePageResponse> response) {
                if (response.isSuccessful()) {
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

    public void setKeys(String token) {
        webAPI.getApi().keys(token).enqueue(new Callback<KeysResponse>() {
            @Override
            public void onResponse(Call<KeysResponse> call, Response<KeysResponse> response) {
                if (response.isSuccessful()) {
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

    public void setRelatedPrducts(String cosumerKey, String consumerSecret, String category, String page,int per_page,String amr_slug,String stock_status) {
        webAPI.getApi().homeResponse(cosumerKey, consumerSecret, category, page,per_page,amr_slug,stock_status).enqueue(new Callback<Categoriesroducts>() {
            @Override
            public void onResponse(Call<Categoriesroducts> call, Response<Categoriesroducts> response) {
                if (response.isSuccessful()) {
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

    public void setProductDetail(String id,String token, String cosumerKey, String consumerSecret) {
        webAPI.getApi().productDetail(id,token, cosumerKey, consumerSecret).enqueue(new Callback<ProductDetailResponse>() {
            @Override
            public void onResponse(Call<ProductDetailResponse> call, Response<ProductDetailResponse> response) {
                if (response.isSuccessful()) {
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


    public void setSubCategory(String category_id) {
        webAPI.getApi().subcategories(category_id).enqueue(new Callback<SubCategory>() {
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                if (response.isSuccessful()) {
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

    public void setClearCart() {
        webAPI.getApi().clearCart().enqueue(new Callback<com.satyam.login.model.ClearCart>() {
            @Override
            public void onResponse(Call<com.satyam.login.model.ClearCart> call, Response<com.satyam.login.model.ClearCart> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.login.model.ClearCart> clearCartResponse = response;
                    clearCart.onSuccessClearCart(clearCartResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.login.model.ClearCart> call, Throwable t) {
                clearCart.onError(t.getMessage());
            }
        });
    }


    public void setGetCartProducts(String token) {
        webAPI.getApi().getCartProducts(token).enqueue(new Callback<com.satyam.myCart.response.GetCartProducts>() {
            @Override
            public void onResponse(Call<com.satyam.myCart.response.GetCartProducts> call, Response<com.satyam.myCart.response.GetCartProducts> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myCart.response.GetCartProducts> cartProductsResponse = response;
                    getCartProducts.onSuccessGetCart(cartProductsResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myCart.response.GetCartProducts> call, Throwable t) {
                getCartProducts.onError(t.getMessage());
            }
        });
    }

    public void setAddToCart(String product_id, String quantity, String variation_id, String token) {
        webAPI.getApi().addtoCart(product_id, quantity, variation_id, token).enqueue(new Callback<com.satyam.productDetails.response.AddToCart>() {
            @Override
            public void onResponse(Call<com.satyam.productDetails.response.AddToCart> call, Response<com.satyam.productDetails.response.AddToCart> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.productDetails.response.AddToCart> addToCartResponse = response;
                    addToCart.onSuccessAddToCart(addToCartResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.productDetails.response.AddToCart> call, Throwable t) {
                addToCart.onError(t.getMessage());
            }
        });
    }

    public void setUpdateCart(String cart_id, String token, String quantity) {
        webAPI.getApi().updateCart(cart_id, token, quantity).enqueue(new Callback<com.satyam.myCart.response.UpdateCart>() {
            @Override
            public void onResponse(Call<com.satyam.myCart.response.UpdateCart> call, Response<com.satyam.myCart.response.UpdateCart> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myCart.response.UpdateCart> updateCartResponse = response;
                    updateCart.onSuccessUpdateCart(updateCartResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myCart.response.UpdateCart> call, Throwable t) {
                updateCart.onError(t.getMessage());
            }
        });
    }

    public void setRemoveCartItem(String cart_id, String user_id, String token) {
        webAPI.getApi().removeCartItem(cart_id, user_id, token).enqueue(new Callback<com.satyam.myCart.response.RemoveCartItem>() {
            @Override
            public void onResponse(Call<com.satyam.myCart.response.RemoveCartItem> call, Response<com.satyam.myCart.response.RemoveCartItem> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myCart.response.RemoveCartItem> removeCartItemResponse = response;
                    removeCartItem.onSuccessRemoveCartItem(removeCartItemResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myCart.response.RemoveCartItem> call, Throwable t) {
                removeCartItem.onError(t.getMessage());
            }
        });
    }

    public void setGetProfile(String token) {
        webAPI.getApi().getProfile(token).enqueue(new Callback<com.satyam.myProfile.response.GetProfile>() {
            @Override
            public void onResponse(Call<com.satyam.myProfile.response.GetProfile> call, Response<com.satyam.myProfile.response.GetProfile> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myProfile.response.GetProfile> getProfileResponse = response;
                    getProfile.onSuccessGetProfile(getProfileResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myProfile.response.GetProfile> call, Throwable t) {
                getProfile.onError(t.getMessage());
            }
        });
    }

    public void setUpdateProfile(String token, String first_name, String last_name, String email, String password, MultipartBody.Part profile) {
        webAPI.getApi().updateProfile(token, first_name, last_name, email, password, profile).enqueue(new Callback<com.satyam.myProfile.response.UpdateProfile>() {
            @Override
            public void onResponse(Call<com.satyam.myProfile.response.UpdateProfile> call, Response<com.satyam.myProfile.response.UpdateProfile> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myProfile.response.UpdateProfile> updateProfileResponse = response;
                    updateProfile.onSuccessUpdateProfile(updateProfileResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myProfile.response.UpdateProfile> call, Throwable t) {
                updateProfile.onError(t.getMessage());
            }
        });
    }

    public void setUpdateProfile1(String token, String first_name, String last_name, String email, String phone, MultipartBody.Part profile)
    {
        webAPI.getApi().updateProfile1(token,first_name,last_name,email,phone,profile).enqueue(new Callback<com.satyam.myProfile.response.UpdateProfile>() {
            @Override
            public void onResponse(Call<com.satyam.myProfile.response.UpdateProfile> call, Response<com.satyam.myProfile.response.UpdateProfile> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myProfile.response.UpdateProfile> updateProfileResponse = response;
                    updateProfile1.onSuccessUpdateProfile1(updateProfileResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myProfile.response.UpdateProfile> call, Throwable t) {
                updateProfile1.onError(t.getMessage());
            }
        });
    }

    public void setGetAddress(String userID, String amr_slug,String consumerkey, String consumerSecret) {
        webAPI.getApi().getAddress(userID, amr_slug,consumerkey, consumerSecret).enqueue(new Callback<com.satyam.addressActivity.response.GetAddress>() {
            @Override
            public void onResponse(Call<com.satyam.addressActivity.response.GetAddress> call, Response<com.satyam.addressActivity.response.GetAddress> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.addressActivity.response.GetAddress> getAddressResponse = response;
                    getAddress.onSuccessGetAddress(getAddressResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.addressActivity.response.GetAddress> call, Throwable t) {
                getAddress.onError(t.getMessage());
            }
        });
    }

    public void setUpdateAddress(String input,String amr_slug, String consumer_key, String consumer_screat, String first_name,String lastname, String address_1,
                                 String address_2, String city, String postcode, String state, String phone,String email,String address_type,String address_type_text,String alternate_phone,String ward_number,String country) {
        webAPI.getApi().updateAddress(input,amr_slug, consumer_key, consumer_screat, first_name, lastname,address_1, address_2, city, postcode, state, phone,email,address_type,address_type_text,alternate_phone,ward_number,country).enqueue(new Callback<com.satyam.addressActivity.response.UpdateAddress>() {
            @Override
            public void onResponse(Call<com.satyam.addressActivity.response.UpdateAddress> call, Response<com.satyam.addressActivity.response.UpdateAddress> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.addressActivity.response.UpdateAddress> updateAddressResponse = response;
                    updateAddress.onSuccessUpdateAddress(updateAddressResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.addressActivity.response.UpdateAddress> call, Throwable t) {
                updateAddress.onError(t.getMessage());
            }
        });
    }

    public void setPlaceOrder1_(String payment_method, String payment_method_title, boolean set_paid, String first_name, String last_name, String address_1,
                              String address_2, String city, String state, String postcode, String country, String email, String phone, String Sfirst_name, String Slast_name,
                              String Saddress_1, String Saddress_2, String Scity, String Sstate, String Spostcode, String Scountry, String productID_quantity
            , String consumer_key, String consumer_secret, String customer_id, String amr_slug) {


        webAPI.getApi().createOrder(payment_method, payment_method_title, set_paid, first_name, last_name, address_1, address_2, city, state, postcode, country, email, phone, Sfirst_name, Slast_name, Saddress_1, Saddress_2, Scity, Sstate, Spostcode, Scountry, productID_quantity, consumer_key, consumer_secret, customer_id, amr_slug).enqueue(new Callback<CreateOrder>() {
            @Override
            public void onResponse(Call<CreateOrder> call, Response<CreateOrder> response) {
                    Response<CreateOrder> createOrderResponse = response;
                    placeOrder.onSuccessPlaceOrder(createOrderResponse);
            }

            @Override
            public void onFailure(Call<CreateOrder> call, Throwable t) {
                placeOrder.onError(t.getMessage());
            }
        });
    }

    public void setPlaceOrder(String payment_method, String payment_method_title, String set_paid, String first_name, String last_name, String address_1,
                              String address_2, String city, String state, String postcode, String country, String email, String phone, String Sfirst_name, String Slast_name,
                              String Saddress_1, String Saddress_2, String Scity, String Sstate, String Spostcode, String Scountry, String productID, String quantity
            , String consumer_key, String consumer_secret, String customer_id, String amr_slug) {
        CreateOrderPojo createOrderPojo = new CreateOrderPojo();
        createOrderPojo.setPaymentMethod(payment_method);
        createOrderPojo.setPaymentMethodTitle(payment_method_title);
        createOrderPojo.setSetPaid(Boolean.valueOf(set_paid));
        CreateOrderPojo.Billing billing = new CreateOrderPojo.Billing();
        billing.setFirstName(first_name);
        billing.setLastName(last_name);
        billing.setAddress1(address_1);
        billing.setAddress2(address_2);
        billing.setCity(city);
        billing.setState(state);
        billing.setPostcode(postcode);
        billing.setCountry(country);
        billing.setEmail(email);
        billing.setPhone(phone);

        createOrderPojo.setBilling(billing);

        CreateOrderPojo.Shipping shipping = new CreateOrderPojo.Shipping();
        shipping.setFirstName(Sfirst_name);
        shipping.setLastName(Slast_name);
        shipping.setAddress1(Saddress_1);
        shipping.setAddress2(Saddress_2);
        shipping.setCity(Scity);
        shipping.setState(Sstate);
        shipping.setPostcode(Spostcode);
        shipping.setCountry(Scountry);
        createOrderPojo.setShipping(shipping);
        CreateOrderPojo.LineItem lineItem = new CreateOrderPojo.LineItem();
        lineItem.setProductId(Integer.valueOf(productID));
        lineItem.setQuantity(Integer.valueOf(quantity));
        ArrayList<CreateOrderPojo.LineItem> lineItems = new ArrayList<>();
        lineItems.add(lineItem);
        createOrderPojo.setLineItems(lineItems);

        webAPI.getApi().createOrder1(createOrderPojo, consumer_key, consumer_secret, customer_id, amr_slug).enqueue(new Callback<CreateOrder>() {
            @Override
            public void onResponse(Call<CreateOrder> call, Response<CreateOrder> response) {
                if (response.isSuccessful()) {
                    Response<CreateOrder> createOrderResponse = response;
                    placeOrder.onSuccessPlaceOrder(createOrderResponse);
                }
            }

            @Override
            public void onFailure(Call<CreateOrder> call, Throwable t) {
                placeOrder.onError(t.getMessage());
            }
        });
    }

    public void setGetAllOrders(String customerID, String cosumerKey, String cosumerSecret, String amr_slug) {
        webAPI.getApi().getAllOrders(customerID, cosumerKey, cosumerSecret, amr_slug).enqueue(new Callback<com.satyam.myOrderList.response.GetAllOrders>() {
            @Override
            public void onResponse(Call<com.satyam.myOrderList.response.GetAllOrders> call, Response<com.satyam.myOrderList.response.GetAllOrders> response) {
                if (response.isSuccessful()) {
                    Response<com.satyam.myOrderList.response.GetAllOrders> getAllOrdersResponse = response;
                    getAllOrders.onSuccessGetAllOrders(getAllOrdersResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.myOrderList.response.GetAllOrders> call, Throwable t) {
                getAllOrders.onError(t.getMessage());
            }
        });
    }

    public void setGetFilterProducts(String consumer_key, String consumer_secret, String category, String min_price, String max_price, String search,String page,int perPage,String stock_status) {
        webAPI.getApi().filterProducts(consumer_key, consumer_secret, category, min_price, max_price, search,page,perPage,stock_status).enqueue(new Callback<FilterResponse>() {
            @Override
            public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                if (response.isSuccessful()) {
                    Response<FilterResponse> filterResponseResponse = response;
                    getFilterProducts.onSuccessGetFilterProducts(filterResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<FilterResponse> call, Throwable t) {
                getFilterProducts.onError(t.getMessage());
            }
        });
    }


    public void setGetOrderDetails(String id,String consumerkey,String consumerSecret,String amr_slug)
    {
        webAPI.getApi().getOrderDetail(id, consumerkey, consumerSecret,amr_slug).enqueue(new Callback<GetOrderDetail>() {
            @Override
            public void onResponse(Call<GetOrderDetail> call, Response<GetOrderDetail> response) {
                if (response.isSuccessful())
                {
                    Response<GetOrderDetail> getOrderDetailResponse = response;
                    getOrderDetails.onSuccessGetOrderDetail(getOrderDetailResponse);
                }
            }

            @Override
            public void onFailure(Call<GetOrderDetail> call, Throwable t) {
                getOrderDetails.onError(t.getMessage());
            }
        });
    }

    public void setGetVariations(String prodctID,String arrayList)
    {
        webAPI.getApi().variations(prodctID,arrayList).enqueue(new Callback<VariationResponse>() {
            @Override
            public void onResponse(Call<VariationResponse> call, Response<VariationResponse> response) {
                if (response.isSuccessful())
                {
                    Response<VariationResponse> variationResponseResponse = response;
                    getVariations.onSuccessVariations(variationResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<VariationResponse> call, Throwable t) {
                getVariations.onError(t.getMessage());
            }
        });
    }

    public void setGetSerchProducts(String search,String stock_status)
    {
        webAPI.getApi().getSeacchProduct(search,stock_status).enqueue(new Callback<GetSearchProductsResponse>() {
            @Override
            public void onResponse(Call<GetSearchProductsResponse> call, Response<GetSearchProductsResponse> response) {
                if (response.isSuccessful())
                {
                    Response<GetSearchProductsResponse> getSearchProductsResponseResponse = response;
                    getSerchProducts.onSuccessSearch(getSearchProductsResponseResponse);
                }
            }

            @Override
            public void onFailure(Call<GetSearchProductsResponse> call, Throwable t) {
                getSerchProducts.onError(t.getMessage());
            }
        });
    }

    public void setGetZone()
    {
        webAPI.getApi().getZones().enqueue(new Callback<GetZones>() {
            @Override
            public void onResponse(Call<GetZones> call, Response<GetZones> response) {
                if (response.isSuccessful())
                {
                    Response<GetZones> getZonesResponse = response;
                    getZone.onSuccessZones(getZonesResponse);
                }
            }

            @Override
            public void onFailure(Call<GetZones> call, Throwable t) {
            getZone.onError(t.getMessage());
            }
        });
    }

    public void setGetCities(String state_id)
    {
        webAPI.getApi().getCities(state_id).enqueue(new Callback<com.satyam.addressActivity.response.GetCities>() {
            @Override
            public void onResponse(Call<com.satyam.addressActivity.response.GetCities> call, Response<com.satyam.addressActivity.response.GetCities> response) {
                if (response.isSuccessful())
                {
                    Response<com.satyam.addressActivity.response.GetCities> getCitiesResponse = response;
                    getCities.onSuccessCities(getCitiesResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.addressActivity.response.GetCities> call, Throwable t) {
                    getCities.onError(t.getMessage());
            }
        });
    }


    public void setPrivacyTD()
    {
        webAPI.getApi().privacyTD().enqueue(new Callback<com.satyam.termsandcondition.PrivacyTD>() {
            @Override
            public void onResponse(Call<com.satyam.termsandcondition.PrivacyTD> call, Response<com.satyam.termsandcondition.PrivacyTD> response) {
                if (response.isSuccessful())
                {
                    Response<com.satyam.termsandcondition.PrivacyTD> privacyTDResponse= response;
                    privacyTD.onSuccess(privacyTDResponse);
                }
            }

            @Override
            public void onFailure(Call<com.satyam.termsandcondition.PrivacyTD> call, Throwable t) {
                privacyTD.onError(t.getMessage());
            }
        });
    }

    public interface LoginCheck {
        void onSuccessLoginCheck(Response<com.satyam.login.model.LoginCheck> loginCheckResponse);

        void onError(String error);
    }

    public interface Login {
        void onsetLogin(Response<com.satyam.login.model.Login> loginResponse);

        void onError(String error);
    }

    public interface HomePage {
        void onSucessHome(Response<HomePageResponse> homePageResponseResponse);

        void onError(String error);
    }

    public interface Keys {
        void onSuccess(Response<KeysResponse> keysResponseResponse);

        void onError(String error);
    }

    public interface RelatedPrducts {
        void onSucessRelated(Response<Categoriesroducts> homePageResponseResponse);

        void onError(String error);
    }

    public interface ProductDetail {
        void onSuccessProductDetail(Response<ProductDetailResponse> productDetailResponseResponse);

        void onError(String error);
    }

    ;

    public interface ProductSubCategories {
        void onSuccessSubcate(Response<SubCategory> subCategoryResponse);

        void onError(String error);
    }

    public interface ClearCart {
        void onSuccessClearCart(Response<com.satyam.login.model.ClearCart> response);

        void onError(String error);
    }

    public interface AddToCart {
        void onSuccessAddToCart(Response<com.satyam.productDetails.response.AddToCart> response);

        void onError(String error);
    }

    public interface GetCartProducts {
        void onSuccessGetCart(Response<com.satyam.myCart.response.GetCartProducts> response);

        void onError(String error);
    }

    public interface UpdateCart {
        void onSuccessUpdateCart(Response<com.satyam.myCart.response.UpdateCart> response);

        void onError(String error);
    }

    public interface RemoveCartItem {
        void onSuccessRemoveCartItem(Response<com.satyam.myCart.response.RemoveCartItem> response);

        void onError(String error);
    }

    public interface GetProfile {
        void onSuccessGetProfile(Response<com.satyam.myProfile.response.GetProfile> response);

        void onError(String error);
    }

    public interface GetAddress {
        void onSuccessGetAddress(Response<com.satyam.addressActivity.response.GetAddress> response);

        void onError(String error);
    }

    public interface UpdateAddress {
        void onSuccessUpdateAddress(Response<com.satyam.addressActivity.response.UpdateAddress> response);

        void onError(String error);
    }

    public interface UpdateProfile {
        void onSuccessUpdateProfile(Response<com.satyam.myProfile.response.UpdateProfile> response);

        void onError(String error);
    }

    public interface UpdateProfile1 {
        void onSuccessUpdateProfile1(Response<com.satyam.myProfile.response.UpdateProfile> response);

        void onError(String error);
    }

    public interface PlaceOrder {
        void onSuccessPlaceOrder(Response<CreateOrder> response);

        void onError(String error);
    }

    public interface PlaceOrder1 {
        void onSuccessPlaceOrder1(Response<CreateOrder> response);

        void onError(String error);
    }

    public interface GetAllOrders {
        void onSuccessGetAllOrders(Response<com.satyam.myOrderList.response.GetAllOrders> response);

        void onError(String error);
    }

    public interface GetFilterProducts {
        void onSuccessGetFilterProducts(Response<FilterResponse> responseResponse);

        void onError(String error);
    }

    public interface GetSearchProducts {
        void onSuccessGetSearchProducts(Response<FilterResponse> responseResponse);

        void onError(String error);
    }


    public interface GetOrderDetails{
        void onSuccessGetOrderDetail(Response<GetOrderDetail> response);
        void onError(String error);
    }

    public interface GetVariations{
        void onSuccessVariations(Response<VariationResponse> variationResponseResponse);
        void onError(String error);
    }

    public interface GetSerchProducts{
        void onSuccessSearch(Response<GetSearchProductsResponse> searchProductsResponseResponse);
        void onError(String error);
    }

    public interface GetZone{
        void onSuccessZones(Response<GetZones> getZonesResponse);
        void onError(String error);
    }

    public interface GetCities{
        void onSuccessCities(Response<com.satyam.addressActivity.response.GetCities> getCitiesResponse);
        void onError(String error);
    }

    public interface SocailLogin{
        void onSuccessSocailLogin(Response<SocialLoginResponse> socialLoginResponse);
        void onError(String error);
    }

    public interface PrivacyTD{
        void onSuccess(Response<com.satyam.termsandcondition.PrivacyTD> privacyTDResponse);
        void onError(String error);
    }
}
