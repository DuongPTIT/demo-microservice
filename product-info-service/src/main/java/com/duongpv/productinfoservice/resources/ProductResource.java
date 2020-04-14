package com.duongpv.productinfoservice.resources;


import com.duongpv.productinfoservice.models.Product;
import com.duongpv.productinfoservice.models.ProductSumary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.key}")
    private String apiKey;

    @GetMapping("/{productId}")
    public Product getProductInfo(@PathVariable("productId") int productId) {

        ProductSumary productSumary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + productId + "?api_key=" + apiKey, ProductSumary.class);

        return new Product(productId, productSumary.getTitle() , 120000, productSumary.getOverview());
    }
}
