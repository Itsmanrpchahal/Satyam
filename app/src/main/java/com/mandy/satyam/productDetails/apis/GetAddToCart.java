package com.mandy.satyam.productDetails.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAddToCart {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("total_cart_products")
        @Expose
        private Integer totalCartProducts;

        public Integer getTotalCartProducts() {
            return totalCartProducts;
        }

        public void setTotalCartProducts(Integer totalCartProducts) {
            this.totalCartProducts = totalCartProducts;
        }

    }

}
