package com.kohls.interview.exercise;

import com.kohls.interview.exercise.models.Movie;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movie, Long> {
  Optional<Movie> findMovieByTitle(String title);
}
