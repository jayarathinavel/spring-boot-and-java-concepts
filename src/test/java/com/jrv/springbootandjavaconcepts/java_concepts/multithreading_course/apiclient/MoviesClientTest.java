package com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.apiclient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.startTimer;
import static com.jrv.springbootandjavaconcepts.java_concepts.multithreading_course.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class MoviesClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();

    MoviesClient moviesClient = new MoviesClient(webClient);

    @Test
    void retrieveMovie() {
        startTimer();
        var movieInfoId = 1L;
        var movie = moviesClient.retrieveMovie(movieInfoId);
        System.out.println("Movie: " + movie);
        timeTaken();
        assert movie!=null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }

    @Test
    void retrieveMovieUsingCompletableFuture() {
        startTimer();
        var movieInfoId = 1L;
        var movie = moviesClient.retrieveMovieUsingCompletableFuture(movieInfoId).join();
        System.out.println("Movie: " + movie);
        timeTaken();
        assert movie!=null;
        assertEquals("Batman Begins", movie.getMovieInfo().getName());
        assert movie.getReviewList().size() == 1;
    }
}