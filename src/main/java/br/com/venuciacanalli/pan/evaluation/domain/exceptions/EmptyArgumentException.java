package br.com.venuciacanalli.pan.evaluation.domain.exceptions;

public class EmptyArgumentException extends RuntimeException{
    private final static String MESSAGE = "The argument %s can't be null or empty.";

    public EmptyArgumentException(Object argumentName){
        super(String.format(MESSAGE, argumentName));
    }
}
