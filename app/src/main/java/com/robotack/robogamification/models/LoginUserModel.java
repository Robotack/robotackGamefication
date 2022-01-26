package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserModel {
 

    @SerializedName("errorCode")
    @Expose
    private String errorCode;
    @SerializedName("descriptionCode")
    @Expose
    private String descriptionCode;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("identifierValue")
    @Expose
    private String identifierValue;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("currentPoints")
    @Expose
    private String currentPoints;
    @SerializedName("accumulativePoints")
    @Expose
    private String accumulativePoints;
    @SerializedName("currentTaskCount")
    @Expose
    private String currentTaskCount;
    @SerializedName("totalTaskCount")
    @Expose
    private String totalTaskCount;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }

    public void setDescriptionCode(String descriptionCode) {
        this.descriptionCode = descriptionCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifierValue() {
        return identifierValue;
    }

    public void setIdentifierValue(String identifierValue) {
        this.identifierValue = identifierValue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(String currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getAccumulativePoints() {
        return accumulativePoints;
    }

    public void setAccumulativePoints(String accumulativePoints) {
        this.accumulativePoints = accumulativePoints;
    }

    public String getCurrentTaskCount() {
        return currentTaskCount;
    }

    public void setCurrentTaskCount(String currentTaskCount) {
        this.currentTaskCount = currentTaskCount;
    }

    public String getTotalTaskCount() {
        return totalTaskCount;
    }

    public void setTotalTaskCount(String totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }


}
