package br.com.api.techchristian.series.exception;

public class ReviewAlreadyExistsException extends RuntimeException {
  public ReviewAlreadyExistsException(String message) {
    super(message);
  }
}
