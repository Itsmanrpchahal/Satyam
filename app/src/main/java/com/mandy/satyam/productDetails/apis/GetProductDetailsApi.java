package com.mandy.satyam.productDetails.apis;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProductDetailsApi {
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
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("special_price")
        @Expose
        private String specialPrice;
        @SerializedName("special_price_start")
        @Expose
        private String specialPriceStart;
        @SerializedName("special_price_end")
        @Expose
        private String specialPriceEnd;
        @SerializedName("qty")
        @Expose
        private Integer qty;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("stock")
        @Expose
        private String stock;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("seo_title")
        @Expose
        private Object seoTitle;
        @SerializedName("seo_key")
        @Expose
        private Object seoKey;
        @SerializedName("seo_desc")
        @Expose
        private Object seoDesc;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("length")
        @Expose
        private Object length;
        @SerializedName("height")
        @Expose
        private Object height;
        @SerializedName("weight")
        @Expose
        private Object weight;
        @SerializedName("features")
        @Expose
        private String features;
        @SerializedName("product_cat_id")
        @Expose
        private Integer productCatId;
        @SerializedName("size_ids")
        @Expose
        private String sizeIds;
        @SerializedName("color_ids")
        @Expose
        private String colorIds;
        @SerializedName("avg_rating")
        @Expose
        private String avgRating;
        @SerializedName("total_users")
        @Expose
        private Integer totalUsers;
        @SerializedName("sizes")
        @Expose
        private List<Size> sizes = null;
        @SerializedName("colors")
        @Expose
        private List<Color> colors = null;
        @SerializedName("product_images")
        @Expose
        private List<ProductImage> productImages = null;

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSpecialPrice() {
            return specialPrice;
        }

        public void setSpecialPrice(String specialPrice) {
            this.specialPrice = specialPrice;
        }

        public String getSpecialPriceStart() {
            return specialPriceStart;
        }

        public void setSpecialPriceStart(String specialPriceStart) {
            this.specialPriceStart = specialPriceStart;
        }

        public String getSpecialPriceEnd() {
            return specialPriceEnd;
        }

        public void setSpecialPriceEnd(String specialPriceEnd) {
            this.specialPriceEnd = specialPriceEnd;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
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

        public Object getSeoTitle() {
            return seoTitle;
        }

        public void setSeoTitle(Object seoTitle) {
            this.seoTitle = seoTitle;
        }

        public Object getSeoKey() {
            return seoKey;
        }

        public void setSeoKey(Object seoKey) {
            this.seoKey = seoKey;
        }

        public Object getSeoDesc() {
            return seoDesc;
        }

        public void setSeoDesc(Object seoDesc) {
            this.seoDesc = seoDesc;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Object getLength() {
            return length;
        }

        public void setLength(Object length) {
            this.length = length;
        }

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }

        public Integer getProductCatId() {
            return productCatId;
        }

        public void setProductCatId(Integer productCatId) {
            this.productCatId = productCatId;
        }

        public String getSizeIds() {
            return sizeIds;
        }

        public void setSizeIds(String sizeIds) {
            this.sizeIds = sizeIds;
        }

        public String getColorIds() {
            return colorIds;
        }

        public void setColorIds(String colorIds) {
            this.colorIds = colorIds;
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

        public List<Size> getSizes() {
            return sizes;
        }

        public void setSizes(List<Size> sizes) {
            this.sizes = sizes;
        }

        public List<Color> getColors() {
            return colors;
        }

        public void setColors(List<Color> colors) {
            this.colors = colors;
        }

        public List<ProductImage> getProductImages() {
            return productImages;
        }

        public void setProductImages(List<ProductImage> productImages) {
            this.productImages = productImages;
        }

    }


    public class Color {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("size")
        @Expose
        private String size;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

    }

    public class ProductImage {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("product_id")
        @Expose
        private Integer productId;
        @SerializedName("media")
        @Expose
        private String media;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public class Size {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("size")
        @Expose
        private String size;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

    }

}
