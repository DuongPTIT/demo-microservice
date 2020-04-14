package com.duongpv.ratingdataservice.models;

import java.util.List;

public class UserRating {
    private String userId;
    private List<Rating> userRatingList;

    public UserRating() {
    }

    public UserRating(String userId, List<Rating> userRatingList) {
        this.userId = userId;
        this.userRatingList = userRatingList;
    }

    public List<Rating> getUserRatingList() {
        return userRatingList;
    }

    public void setUserRatingList(List<Rating> userRatingList) {
        this.userRatingList = userRatingList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
