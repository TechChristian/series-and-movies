package br.com.api.techchristian.series.exception;

public class EmailAlreadyException extends RuntimeException {
  public EmailAlreadyException(String message) {
    super(message);
  }
}
