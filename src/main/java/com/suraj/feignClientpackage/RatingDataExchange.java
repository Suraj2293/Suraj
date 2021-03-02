package com.suraj.feignClientpackage;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suraj.moviecatalogservice.ListOfRating;
@FeignClient(name="netflix-zuul-api-gateway-server")
//@FeignClient("rating-data-service")
@RibbonClient(name="rating-data-service")
public interface RatingDataExchange {
@GetMapping("/rating-data-service/ratingData/ratings/{userId}")	
public ListOfRating getListOfRating(@PathVariable("userId") String userId);
}
