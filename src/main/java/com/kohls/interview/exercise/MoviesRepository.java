package com.kohls.interview.exercise;

import com.kohls.interview.exercise.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, Long> {
}
