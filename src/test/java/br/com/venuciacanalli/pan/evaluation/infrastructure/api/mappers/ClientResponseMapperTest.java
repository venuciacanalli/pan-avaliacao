package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientResponseMapperTest {

    @InjectMocks
    private ClientResponseMapper clientResponseMapper;

    @Test
    @DisplayName("When run toResponse it should return client")
    void whenRunToResponseItShouldReturnClient() {
        String cpf = "52350631044";
        String name = "John Smith";
        Client clientMock = mock(Client.class);
        when(clientMock.cpf()).thenReturn(cpf);
        when(clientMock.name()).thenReturn(name);

        ClientResponse response = this.clientResponseMapper.toResponse(clientMock);

        assertNotNull(response);
        assertEquals(cpf, response.cpf());
        assertEquals(name, response.name());
    }

    @Test
    @DisplayName("When run toResponse with null client it should throw illegal argument exception")
    void whenRunToDomainWithNullClientEntityItShouldThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> this.clientResponseMapper.toResponse(null));
        assertEquals("client can't be null", exception.getMessage());
    }

}