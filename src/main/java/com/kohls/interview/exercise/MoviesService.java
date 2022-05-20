package com.kohls.interview.exercise;

import com.kohls.interview.exercise.exceptions.MoviesException;
import com.kohls.interview.exercise.interfaces.SaveMovieToFavorites;
import com.kohls.interview.exercise.models.Movie;
import com.kohls.interview.exercise.models.OMDBMovie;
import java.io.IOException;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Primary
public class MoviesService implements SaveMovieToFavorites {
  private final MoviesRepository repository;
  private final OMDBApiService apiService;

  public Movie saveMovieToFavorites(final String title) {
    Consumer<String> apiCall = (err) -> System.out.println("error api call ");
    try {
      OMDBMovie movie = apiService.findMovieByTitle(title);
      if (movie == null) {
        throw new MoviesException("Movie not found", apiCall);
      }
      // logic checking
      repository.findMovieByTitle(movie.getTitle()).ifPresent(t->{
        throw new MoviesException("this movie is already present",apiCall);
      });

      System.out.println("We got a movie: \n" + movie);
      final Movie favoriteToAdd = new Movie();
      favoriteToAdd.setTitle(movie.getTitle());
      favoriteToAdd.setDescription(movie.getPlot());

      return repository.save(favoriteToAdd);

    } catch (IOException e) {
      e.printStackTrace();
      throw new MoviesException(e.getMessage(), apiCall);
    }
  }
}
