package com.kohls.interview.exercise;

import com.kohls.interview.exercise.models.Movie;
import com.kohls.interview.exercise.models.OMDBMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MoviesController {
    @Autowired
    OMDBApiService apiService;
    @Autowired
    MoviesService service;

    @GetMapping("/movies")
    public ResponseEntity<Movie> addMovieToFavorites(@RequestParam(value = "title") String title) {
        try {
            OMDBMovie movie = apiService.findMovieByTitle(title);
            System.out.println("We got a movie: \n" + movie);
            Movie favoriteToAdd = new Movie();
            favoriteToAdd.setTitle(movie.getTitle());
            favoriteToAdd.setDescription(movie.getPlot());
            return new ResponseEntity<Movie>(service.saveMovieToFavorites(favoriteToAdd), HttpStatus.OK);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
