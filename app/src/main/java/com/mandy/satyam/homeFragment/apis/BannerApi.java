package com.mandy.satyam.homeFragment.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BannerApi {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("banner")
        @Expose
        private String banner;
        @SerializedName("product_cat_ids")
        @Expose
        private String productCatIds;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getProductCatIds() {
            return productCatIds;
        }

        public void setProductCatIds(String productCatIds) {
            this.productCatIds = productCatIds;
        }

    }
}
