package com.mandy.satyam.myOrderList.myorderdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsApi {
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
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("order_delivered_on")
        @Expose
        private String orderDeliveredOn;
        @SerializedName("order_received_by")
        @Expose
        private Object orderReceivedBy;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrderDeliveredOn() {
            return orderDeliveredOn;
        }

        public void setOrderDeliveredOn(String orderDeliveredOn) {
            this.orderDeliveredOn = orderDeliveredOn;
        }

        public Object getOrderReceivedBy() {
            return orderReceivedBy;
        }

        public void setOrderReceivedBy(Object orderReceivedBy) {
            this.orderReceivedBy = orderReceivedBy;
        }

    }
}
