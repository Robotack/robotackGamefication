package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckTaskModel {


    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("descriptionCode")
    @Expose
    private String descriptionCode;

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

}
