package com.mandy.satyam.myCart.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCartProducts {

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

        @SerializedName("cart_id")
        @Expose
        private String cartId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_price")
        @Expose
        private String productPrice;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("quantity")
        @Expose
        private int quantity;
        @SerializedName("product_image")
        @Expose
        private String productImage;
        @SerializedName("line_total")
        @Expose
        private String lineTotal;

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(String productPrice) {
            this.productPrice = productPrice;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getLineTotal() {
            return lineTotal;
        }

        public void setLineTotal(String lineTotal) {
            this.lineTotal = lineTotal;
        }

    }
}