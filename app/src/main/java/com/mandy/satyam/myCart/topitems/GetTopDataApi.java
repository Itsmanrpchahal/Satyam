package com.mandy.satyam.myCart.topitems;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class GetTopDataApi {
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

        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("product_cat_id")
        @Expose
        private Integer productCatId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("product_slug")
        @Expose
        private String productSlug;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("special_price")
        @Expose
        private Integer specialPrice;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("avg_rating")
        @Expose
        private String avgRating;
        @SerializedName("total_rating_users")
        @Expose
        private Integer totalRatingUsers;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getProductCatId() {
            return productCatId;
        }

        public void setProductCatId(Integer productCatId) {
            this.productCatId = productCatId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductSlug() {
            return productSlug;
        }

        public void setProductSlug(String productSlug) {
            this.productSlug = productSlug;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(Integer specialPrice) {
            this.specialPrice = specialPrice;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getAvgRating() {
            return avgRating;
        }

        public void setAvgRating(String avgRating) {
            this.avgRating = avgRating;
        }

        public Integer getTotalRatingUsers() {
            return totalRatingUsers;
        }

        public void setTotalRatingUsers(Integer totalRatingUsers) {
            this.totalRatingUsers = totalRatingUsers;
        }

    }
}
