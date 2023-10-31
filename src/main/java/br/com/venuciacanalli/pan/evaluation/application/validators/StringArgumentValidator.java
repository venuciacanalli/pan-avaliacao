package br.com.venuciacanalli.pan.evaluation.application.validators;

import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;

public class StringArgumentValidator {

    public void validate(String argName, String argValue){
        if(argValue == null || argValue.trim().isEmpty())
            throw new EmptyArgumentException(argName);
    }
}
