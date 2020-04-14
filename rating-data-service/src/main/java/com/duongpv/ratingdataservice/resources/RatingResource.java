package com.duongpv.ratingdataservice.resources;

import com.duongpv.ratingdataservice.models.Rating;
import com.duongpv.ratingdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

    @GetMapping("/{productId}")
    public Rating getRating(@PathVariable("productId") int productId) {
        return new Rating(productId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating(500, 4),
                new Rating(501,3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRatingList(ratings);
        userRating.setUserId(userId);
        return userRating;
    }

}
