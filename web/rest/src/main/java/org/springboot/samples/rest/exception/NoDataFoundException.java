package org.springboot.samples.rest.exception;

public class NoDataFoundException extends RuntimeException{
    public NoDataFoundException() {
        super("No data found");
    }
}
