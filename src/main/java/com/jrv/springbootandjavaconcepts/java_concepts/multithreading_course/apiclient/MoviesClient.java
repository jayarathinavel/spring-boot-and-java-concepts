package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.apiclient;

import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.movie.Movie;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.movie.MovieInfo;
import com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.domain.movie.Review;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MoviesClient {
    private final WebClient webClient;

    public MoviesClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Movie retrieveMovie(Long movieInfoId){
        var movieInfo = invokeMovieInfo(movieInfoId);
        var reviewsInfo = invokeReviews(movieInfoId);
        return new Movie(movieInfo, reviewsInfo);
    }

    public CompletableFuture<Movie> retrieveMovieUsingCompletableFuture(Long movieInfoId){
        var movieInfo = CompletableFuture.supplyAsync(()->invokeMovieInfo(movieInfoId));
        var reviewsInfo = CompletableFuture.supplyAsync(()->invokeReviews(movieInfoId));
        return movieInfo.thenCombine(reviewsInfo, Movie::new);
    }

    private MovieInfo invokeMovieInfo(Long movieInfoId) {
        var moviesInfoUrlPath = "/v1/movie_infos/{movieInfoId}";
        return webClient.get()
                .uri(moviesInfoUrlPath, movieInfoId)
                .retrieve()
                .bodyToMono(MovieInfo.class)
                .block();
    }

    private List<Review> invokeReviews(Long movieInfoId) {
        var reviewUri = UriComponentsBuilder.fromUriString("/v1/reviews")
                        .queryParam("movieInfoId", movieInfoId)
                                .buildAndExpand()
                                        .toString();
        return webClient.get()
                .uri(reviewUri)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }
}
