package com.duongpv.productcatalogservice.resources;

import com.duongpv.productcatalogservice.models.CatalogItem;
import com.duongpv.productcatalogservice.models.Product;
import com.duongpv.productcatalogservice.models.Rating;
import com.duongpv.productcatalogservice.models.UserRating;
import com.duongpv.productcatalogservice.services.ProductInfo;
import com.duongpv.productcatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RefreshScope
@RequestMapping("/catalog")
public class ProductCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private ProductInfo productInfo;

    @Autowired
    private UserRatingInfo userRatingInfo;

    @Autowired
    private Environment environment;

    @Value("${duong.author}")
    private String author;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = userRatingInfo.getUserRating(userId);
        return ratings.getUserRatingList().stream().map(rating -> {
            return productInfo.getCatalogItem(rating);
        })
                .collect(Collectors.toList());

    }

    @GetMapping("/envdetails")
    public String getEnvDetails() {
        return environment.getActiveProfiles().toString();
    }

    @GetMapping("/author")
    public String getAuthor() {
        return author;
    }





}

    /*
    Product product = webClientBuilder.build()
            .get()
            .uri("http://localhost:8082/product/" + rating.getProductId())
            .retrieve()
            .bodyToMono(Product.class)
            .block();
    */