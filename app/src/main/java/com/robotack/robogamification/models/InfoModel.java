package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoModel {



        @SerializedName("errorCode")
        @Expose
        private Integer errorCode;
        @SerializedName("descriptionCode")
        @Expose
        private Object descriptionCode;
        @SerializedName("title")
        @Expose
        private Object title;
        @SerializedName("description")
        @Expose
        private String description;

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        public Object getDescriptionCode() {
            return descriptionCode;
        }

        public void setDescriptionCode(Object descriptionCode) {
            this.descriptionCode = descriptionCode;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

}
