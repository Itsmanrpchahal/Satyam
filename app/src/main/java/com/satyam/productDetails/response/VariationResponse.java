package com.satyam.productDetails.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariationResponse {

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

        @SerializedName("variation_id")
        @Expose
        private Integer variationId;
        @SerializedName("variation_name")
        @Expose
        private String variationName;
        @SerializedName("variation_price")
        @Expose
        private String variationPrice;
        @SerializedName("variation_regular_price")
        @Expose
        private String variationRegularPrice;
        @SerializedName("variation_sale_price")
        @Expose
        private String variationSalePrice;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("currency_symbol")
        @Expose
        private String currencySymbol;

        public Integer getVariationId() {
            return variationId;
        }

        public void setVariationId(Integer variationId) {
            this.variationId = variationId;
        }

        public String getVariationName() {
            return variationName;
        }

        public void setVariationName(String variationName) {
            this.variationName = variationName;
        }

        public String getVariationPrice() {
            return variationPrice;
        }

        public void setVariationPrice(String variationPrice) {
            this.variationPrice = variationPrice;
        }

        public String getVariationRegularPrice() {
            return variationRegularPrice;
        }

        public void setVariationRegularPrice(String variationRegularPrice) {
            this.variationRegularPrice = variationRegularPrice;
        }

        public String getVariationSalePrice() {
            return variationSalePrice;
        }

        public void setVariationSalePrice(String variationSalePrice) {
            this.variationSalePrice = variationSalePrice;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public void setCurrencySymbol(String currencySymbol) {
            this.currencySymbol = currencySymbol;
        }

    }
}