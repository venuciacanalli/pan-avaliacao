package br.com.venuciacanalli.pan.evaluation.application.validators;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientExistsValidatorTest {

    @InjectMocks
    private ClientExistsValidator clientExistsValidator;

    @Mock
    private IClientGateway clientGateway;

    @Test
    void whenCpfExistsNoThrowException() {
        String cpf = "52350631044";
        when(clientGateway.existsByCpf(cpf)).thenReturn(true);
        this.clientExistsValidator.validate(cpf);
        verify(clientGateway, times(1)).existsByCpf(cpf);
    }

    @Test
    void whenCpfNotExistsTrowObjectWithAttributeNotFoundException() {
        String cpf = "52350631044";
        when(clientGateway.existsByCpf(cpf)).thenReturn(false);
        Exception exception = assertThrows(ObjectWithAttributeNotFoundException.class, () -> this.clientExistsValidator.validate(cpf));
        assertEquals("Client with cpf 52350631044 not found", exception.getMessage());
    }
}