package br.com.venuciacanalli.pan.evaluation.infrastructure.api.errors;

import br.com.venuciacanalli.pan.evaluation.domain.exceptions.BadGatewayException;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler(ObjectWithAttributeNotFoundException.class)
    public ResponseEntity<Object> handle404Errors(ObjectWithAttributeNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmptyArgumentException.class)
    public ResponseEntity<Object> handle404Errors(EmptyArgumentException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<Object> handle502Errors(BadGatewayException exception){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(exception.getMessage());
    }
}