package com.robotack.robogamification.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeasonDetailsModel {

        @SerializedName("errorCode")
        @Expose
        private Integer errorCode;
        @SerializedName("descriptionCode")
        @Expose
        private String descriptionCode;
        @SerializedName("totalTaskCount")
        @Expose
        private Integer totalTaskCount;
        @SerializedName("completedTaskCount")
        @Expose
        private Integer completedTaskCount;
        @SerializedName("minCompleteTaskCount")
        @Expose
        private Integer minCompleteTaskCount;
        @SerializedName("minReward")
        @Expose
        private Integer minReward;
        @SerializedName("unitId")
        @Expose
        private Integer unitId;

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("gainedPoints")
        @Expose
        private String gainedPoints;

    public String getGainedPoints() {
        return gainedPoints;
    }

    public void setGainedPoints(String gainedPoints) {
        this.gainedPoints = gainedPoints;
    }

    @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("startDate")
        @Expose
        private String startDate;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("tasks")
        @Expose
        private List<Task> tasks = null;

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

        public Integer getMinCompleteTaskCount() {
            return minCompleteTaskCount;
        }

        public void setMinCompleteTaskCount(Integer minCompleteTaskCount) {
            this.minCompleteTaskCount = minCompleteTaskCount;
        }

        public Integer getMinReward() {
            return minReward;
        }

        public void setMinReward(Integer minReward) {
            this.minReward = minReward;
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

        public List<Task> getTasks() {
            return tasks;
        }

        public void setTasks(List<Task> tasks) {
            this.tasks = tasks;
        }



    public class Task {

        @SerializedName("taskId")
        @Expose
        private Integer taskId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("taskImage")
        @Expose
        private String taskImage;
        @SerializedName("taskStatus")
        @Expose
        private String taskStatus;

        public Integer getTaskId() {
            return taskId;
        }

        public void setTaskId(Integer taskId) {
            this.taskId = taskId;
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

        public String getTaskImage() {
            return taskImage;
        }

        public void setTaskImage(String taskImage) {
            this.taskImage = taskImage;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        @SerializedName("special")
        @Expose
        private String special;
        @SerializedName("remainingTime")
        @Expose
        private String remainingTime;

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getRemainingTime() {
            return remainingTime;
        }

        public void setRemainingTime(String remainingTime) {
            this.remainingTime = remainingTime;
        }
    }
}
