package br.com.venuciacanalli.pan.evaluation.application.validators;

import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringArgumentValidatorTest {

    @InjectMocks
    private StringArgumentValidator stringArgumentValidator;

    @Test
    void whenStringArgumentIsValidNoThrowException() {
        this.stringArgumentValidator.validate("cpf","52350631044");
    }

    @Test
    void whenStringArgumentIsNullThrowEmptyArgumentException() {
        Exception exception = assertThrows(EmptyArgumentException.class, () -> this.stringArgumentValidator.validate("fieldName",null));
        assertEquals("The argument fieldName can't be null or empty.", exception.getMessage());
    }

    @Test
    void whenStringArgumentIsEmptyThrowEmptyArgumentException() {
        Exception exception = assertThrows(EmptyArgumentException.class, () -> this.stringArgumentValidator.validate("fieldName"," "));
        assertEquals("The argument fieldName can't be null or empty.", exception.getMessage());
    }
}