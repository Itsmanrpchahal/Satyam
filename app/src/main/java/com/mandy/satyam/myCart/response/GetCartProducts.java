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
@SerializedName("total_quantity")
@Expose
private String total_quantity;
@SerializedName("subtotal")
@Expose
private String subtotal;
@SerializedName("tax")
@Expose
private String tax;
@SerializedName("shipping")
@Expose
private String shipping;

    public String getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(String total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @SerializedName("total")
@Expose
private String total;
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

        public int getVariation_id() {
            return variation_id;
        }

        public void setVariation_id(int variation_id) {
            this.variation_id = variation_id;
        }

        @SerializedName("variation_id")
        @Expose
        private int variation_id;
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