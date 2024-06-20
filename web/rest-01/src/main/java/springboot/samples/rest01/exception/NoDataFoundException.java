package springboot.samples.rest01.exception;

public class NoDataFoundException extends RuntimeException {

  public NoDataFoundException() {
    super("No data found");
  }
}
