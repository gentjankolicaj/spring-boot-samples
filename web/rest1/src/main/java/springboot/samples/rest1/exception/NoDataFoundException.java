package springboot.samples.rest1.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException() {
        super("No data found");
    }
}
