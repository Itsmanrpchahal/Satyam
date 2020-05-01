package com.satyam.termsandcondition;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TermsConditionApi {
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

        @SerializedName("terms and conditions")
        @Expose
        private String termsAndConditions;
        @SerializedName("privecy policy")
        @Expose
        private String privecyPolicy;

        public String getTermsAndConditions() {
            return termsAndConditions;
        }

        public void setTermsAndConditions(String termsAndConditions) {
            this.termsAndConditions = termsAndConditions;
        }

        public String getPrivecyPolicy() {
            return privecyPolicy;
        }

        public void setPrivecyPolicy(String privecyPolicy) {
            this.privecyPolicy = privecyPolicy;
        }

    }

}
