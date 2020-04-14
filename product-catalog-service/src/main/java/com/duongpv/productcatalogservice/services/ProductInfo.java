package com.duongpv.productcatalogservice.services;

import com.duongpv.productcatalogservice.models.CatalogItem;
import com.duongpv.productcatalogservice.models.Product;
import com.duongpv.productcatalogservice.models.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Product product = restTemplate.getForObject("http://PRODUCT-INFO-SERVICE/product/" + rating.getProductId(), Product.class);
        return new CatalogItem(product.getName(), product.getDesc(), rating.getRating());
    }
    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie not found", "", rating.getRating());
    }
}
