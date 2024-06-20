package springboot.samples.jdbctemplate02.exception;

public class MovieNotFoundException extends NotFoundException {

  public MovieNotFoundException() {
  }

  public MovieNotFoundException(String message) {
    super(message);
  }

  public MovieNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public MovieNotFoundException(Throwable cause) {
    super(cause);
  }

  public MovieNotFoundException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
