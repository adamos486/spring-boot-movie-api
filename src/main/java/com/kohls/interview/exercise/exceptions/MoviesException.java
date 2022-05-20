package com.kohls.interview.exercise.exceptions;

import java.util.function.Consumer;

public class MoviesException extends RuntimeException {
  public MoviesException(String errorMessage, Consumer<String> funcName){
    // function calls error api
    // error call
    funcName.accept(errorMessage);
    throw new RuntimeException(errorMessage);
  }
}
