package com.suraj.moviecatalogservice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.suraj.feignClientpackage.MovieInfoExchange;
import com.suraj.feignClientpackage.RatingDataExchange;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
	private RestTemplate restTemplate;
    
    @Autowired
    private RatingDataExchange ratingDataExchange;
    
    @Autowired
    private MovieInfoExchange movieInfoExchange;
    
	@RequestMapping("/{userId}")
	@HystrixCommand(fallbackMethod="getFallBackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		//ListOfRating object=restTemplate.getForObject("http://rating-data-service/ratingData/ratings/sss", ListOfRating.class);
		ListOfRating object=ratingDataExchange.getListOfRating(userId);
		return object.getList().stream().map(a -> {
	//	Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+a.getMovieId(), Movie.class);	
		Movie movie=movieInfoExchange.getMovieInfoDetails(a.getMovieId());	
		return new CatalogItem(movie.getName(), "fff", Integer.parseInt(a.getRating()));	
		}).collect(Collectors.toList());
		
		
	}	
	
	public List<CatalogItem> getFallBackCatalog(@PathVariable("userId") String userId){
		return Arrays.asList(new CatalogItem("No Movie","",0));
	}
}
