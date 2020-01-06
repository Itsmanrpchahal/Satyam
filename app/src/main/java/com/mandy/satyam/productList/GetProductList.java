package com.mandy.satyam.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductList {
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
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special_price")
        @Expose
        private Integer specialPrice;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("avg_rating")
        @Expose
        private String avgRating;
        @SerializedName("total_users")
        @Expose
        private Integer totalUsers;
        @SerializedName("product_cat_id")
        @Expose
        private Integer productCatId;

        public Integer getProductCatId() {
            return productCatId;
        }

        public void setProductCatId(Integer productCatId) {
            this.productCatId = productCatId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(Integer specialPrice) {
            this.specialPrice = specialPrice;
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

        public Integer getTotalUsers() {
            return totalUsers;
        }

        public void setTotalUsers(Integer totalUsers) {
            this.totalUsers = totalUsers;
        }

    }
}
