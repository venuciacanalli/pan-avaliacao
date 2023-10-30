package br.com.venuciacanalli.pan.evaluation.infrastructure.api.errors;

import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppErrorAdvice {

    @ExceptionHandler(ObjectWithAttributeNotFoundException.class)
    public ResponseEntity handle404Errors(){
        return ResponseEntity.notFound().build();
    }

}
