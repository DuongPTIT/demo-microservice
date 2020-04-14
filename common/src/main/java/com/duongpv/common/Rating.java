package com.duongpv.common;

public class Rating {
    private int productId;
    private int rating;

    public Rating() {
    }

    public Rating(int productId, int rating) {
        this.productId = productId;
        this.rating = rating;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
