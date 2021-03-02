package com.suraj.feignClientpackage;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.suraj.moviecatalogservice.Movie;

//@FeignClient("movie-info-service")----
@FeignClient(name="netflix-zuul-api-gateway-server")	
@RibbonClient(name="movie-info-service")
public interface MovieInfoExchange {
	@GetMapping("/movie-info-service/movies/{movieId}")
	public Movie getMovieInfoDetails(@PathVariable("movieId") String movieId);
}
