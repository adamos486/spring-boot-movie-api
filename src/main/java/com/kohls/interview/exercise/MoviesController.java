package com.kohls.interview.exercise;

import com.kohls.interview.exercise.interfaces.SaveMovieToFavorites;
import com.kohls.interview.exercise.models.Movie;
import com.kohls.interview.exercise.models.OMDBMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MoviesController {

  private final SaveMovieToFavorites moviesService;

  @GetMapping("/movies")
  public ResponseEntity<Movie> addMovieToFavorites(@NonNull @RequestParam(value = "title") String title) {
    return new ResponseEntity<>(moviesService.saveMovieToFavorites(title), HttpStatus.OK);
  }
}
