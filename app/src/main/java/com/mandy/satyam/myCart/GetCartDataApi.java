package com.mandy.satyam.myCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCartDataApi {
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

        @SerializedName("total_products")
        @Expose
        private Integer totalProducts;
        @SerializedName("total_cart_price")
        @Expose
        private Integer totalCartPrice;
        @SerializedName("total_cart_products")
        @Expose
        private List<TotalCartProduct> totalCartProducts = null;

        public Integer getTotalProducts() {
            return totalProducts;
        }

        public void setTotalProducts(Integer totalProducts) {
            this.totalProducts = totalProducts;
        }

        public Integer getTotalCartPrice() {
            return totalCartPrice;
        }

        public void setTotalCartPrice(Integer totalCartPrice) {
            this.totalCartPrice = totalCartPrice;
        }

        public List<TotalCartProduct> getTotalCartProducts() {
            return totalCartProducts;
        }

        public void setTotalCartProducts(List<TotalCartProduct> totalCartProducts) {
            this.totalCartProducts = totalCartProducts;
        }

    }

    public class TotalCartProduct {

        @SerializedName("cart_id")
        @Expose
        private Integer cartId;
        @SerializedName("product_order_quantity")
        @Expose
        private String productOrderQuantity;
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
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("size")
        @Expose
        private String size;

        public Integer getCartId() {
            return cartId;
        }

        public void setCartId(Integer cartId) {
            this.cartId = cartId;
        }

        public String getProductOrderQuantity() {
            return productOrderQuantity;
        }

        public void setProductOrderQuantity(String productOrderQuantity) {
            this.productOrderQuantity = productOrderQuantity;
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

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

    }
}
