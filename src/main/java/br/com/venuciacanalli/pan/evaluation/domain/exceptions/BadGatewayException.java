package br.com.venuciacanalli.pan.evaluation.domain.exceptions;

public class BadGatewayException extends RuntimeException{
    private final static String MESSAGE = "The application received an invalid response while acting with external application (%s) : %s";

    public BadGatewayException(Object externalAppName, String cause){
        super(String.format(MESSAGE, externalAppName, (cause== null ? "" : cause)));
    }
}
