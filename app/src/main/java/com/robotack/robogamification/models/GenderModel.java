package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenderModel {


    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;

        private boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("mainImage")
        @Expose
        private Boolean mainImage;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Boolean getMainImage() {
            return mainImage;
        }

        public void setMainImage(Boolean mainImage) {
            this.mainImage = mainImage;
        }

    }


        @SerializedName("errorCode")
        @Expose
        private Integer errorCode;
        @SerializedName("descriptionCode")
        @Expose
        private String descriptionCode;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescriptionCode() {
            return descriptionCode;
        }

        public void setDescriptionCode(String descriptionCode) {
            this.descriptionCode = descriptionCode;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

}
