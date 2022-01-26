package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SeasonModel implements Serializable{

    public class ActiveUnit implements Serializable{

        @SerializedName("errorCode")
        @Expose
        private Object errorCode;
        @SerializedName("descriptionCode")
        @Expose
        private Object descriptionCode;
        @SerializedName("totalTaskCount")
        @Expose
        private Integer totalTaskCount;
        @SerializedName("completedTaskCount")
        @Expose
        private Integer completedTaskCount;
        @SerializedName("unitId")
        @Expose
        private Integer unitId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;

        public Object getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Object errorCode) {
            this.errorCode = errorCode;
        }

        public Object getDescriptionCode() {
            return descriptionCode;
        }

        public void setDescriptionCode(Object descriptionCode) {
            this.descriptionCode = descriptionCode;
        }

        public Integer getTotalTaskCount() {
            return totalTaskCount;
        }

        public void setTotalTaskCount(Integer totalTaskCount) {
            this.totalTaskCount = totalTaskCount;
        }

        public Integer getCompletedTaskCount() {
            return completedTaskCount;
        }

        public void setCompletedTaskCount(Integer completedTaskCount) {
            this.completedTaskCount = completedTaskCount;
        }

        public Integer getUnitId() {
            return unitId;
        }

        public void setUnitId(Integer unitId) {
            this.unitId = unitId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

    }

    @SerializedName("errorCode")
    @Expose
    private Integer errorCode;
    @SerializedName("descriptionCode")
    @Expose
    private String descriptionCode;
    @SerializedName("activeUnit")
    @Expose
    private ActiveUnit activeUnit;
    @SerializedName("history")
    @Expose
    private List<History> history = null;
    @SerializedName("user")
    @Expose
    private User user;

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

    public ActiveUnit getActiveUnit() {
        return activeUnit;
    }

    public void setActiveUnit(ActiveUnit activeUnit) {
        this.activeUnit = activeUnit;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public class History implements Serializable{

        @SerializedName("unitId")
        @Expose
        private Integer unitId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("totalTaskCount")
        @Expose
        private Integer totalTaskCount;
        @SerializedName("completedTaskCount")
        @Expose
        private Integer completedTaskCount;
        @SerializedName("reward")
        @Expose
        private Integer reward;
        @SerializedName("gainedPoints")
        @Expose
        private String gainedPoints;

        public Integer getUnitId() {
            return unitId;
        }

        public void setUnitId(Integer unitId) {
            this.unitId = unitId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Integer getTotalTaskCount() {
            return totalTaskCount;
        }

        public void setTotalTaskCount(Integer totalTaskCount) {
            this.totalTaskCount = totalTaskCount;
        }

        public Integer getCompletedTaskCount() {
            return completedTaskCount;
        }

        public void setCompletedTaskCount(Integer completedTaskCount) {
            this.completedTaskCount = completedTaskCount;
        }

        public String getGainedPoints() {
            return gainedPoints;
        }

        public void setGainedPoints(String gainedPoints) {
            this.gainedPoints = gainedPoints;
        }

        public Integer getReward() {
            return reward;
        }

        public void setReward(Integer reward) {
            this.reward = reward;
        }

    }

    public static class User implements Serializable {

        @SerializedName("errorCode")
        @Expose
        private Object errorCode;
        @SerializedName("descriptionCode")
        @Expose
        private Object descriptionCode;
        @SerializedName("id")
        @Expose
        private Integer id;
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
        private Integer currentPoints;
        @SerializedName("accumulativePoints")
        @Expose
        private Integer accumulativePoints;
        @SerializedName("currentTaskCount")
        @Expose
        private Integer currentTaskCount;
        @SerializedName("totalTaskCount")
        @Expose
        private Integer totalTaskCount;

        public Object getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Object errorCode) {
            this.errorCode = errorCode;
        }

        public Object getDescriptionCode() {
            return descriptionCode;
        }

        public void setDescriptionCode(Object descriptionCode) {
            this.descriptionCode = descriptionCode;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
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

        public Integer getCurrentPoints() {
            return currentPoints;
        }

        public void setCurrentPoints(Integer currentPoints) {
            this.currentPoints = currentPoints;
        }

        public Integer getAccumulativePoints() {
            return accumulativePoints;
        }

        public void setAccumulativePoints(Integer accumulativePoints) {
            this.accumulativePoints = accumulativePoints;
        }

        public Integer getCurrentTaskCount() {
            return currentTaskCount;
        }

        public void setCurrentTaskCount(Integer currentTaskCount) {
            this.currentTaskCount = currentTaskCount;
        }

        public Integer getTotalTaskCount() {
            return totalTaskCount;
        }

        public void setTotalTaskCount(Integer totalTaskCount) {
            this.totalTaskCount = totalTaskCount;
        }

    }
}
