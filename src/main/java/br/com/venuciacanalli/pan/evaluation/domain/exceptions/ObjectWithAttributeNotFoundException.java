package br.com.venuciacanalli.pan.evaluation.domain.exceptions;

public class ObjectWithAttributeNotFoundException extends RuntimeException{

    private final static String MESSAGE = "%s with %s %s not found";

    public ObjectWithAttributeNotFoundException(Object objectName, String attributeName, Object attributeValue){
        super(String.format(MESSAGE, objectName, attributeName, attributeValue));
    }
}