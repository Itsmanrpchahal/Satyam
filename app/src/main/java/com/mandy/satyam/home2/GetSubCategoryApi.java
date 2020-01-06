package com.mandy.satyam.home2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSubCategoryApi {

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

        @SerializedName("Explore_more")
        @Expose
        private List<ExploreMore> exploreMore = null;
        @SerializedName("bottom_wear")
        @Expose
        private List<BottomWear> bottomWear = null;
        @SerializedName("Top_Wear")
        @Expose
        private List<TopWear> topWear = null;

        public List<ExploreMore> getExploreMore() {
            return exploreMore;
        }

        public void setExploreMore(List<ExploreMore> exploreMore) {
            this.exploreMore = exploreMore;
        }

        public List<BottomWear> getBottomWear() {
            return bottomWear;
        }

        public void setBottomWear(List<BottomWear> bottomWear) {
            this.bottomWear = bottomWear;
        }

        public List<TopWear> getTopWear() {
            return topWear;
        }

        public void setTopWear(List<TopWear> topWear) {
            this.topWear = topWear;
        }

    }

    public class BottomWear {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }


    public class ExploreMore {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

    public class TopWear {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }


}
