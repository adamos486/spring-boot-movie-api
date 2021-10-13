package com.kohls.interview.exercise;

import com.kohls.interview.exercise.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {
    @Autowired
    MoviesRepository repository;

    public Movie saveMovieToFavorites(Movie movie) {
        return repository.save(movie);
    }
}
