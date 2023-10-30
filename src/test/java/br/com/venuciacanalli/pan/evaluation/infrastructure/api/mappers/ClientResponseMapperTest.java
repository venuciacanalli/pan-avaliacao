package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClientResponseMapperTest {

    @InjectMocks
    private ClientResponseMapper clientResponseMapper;

    @Mock
    private AddressResponseMapper addressResponseMapper;

    @Test
    @DisplayName("When run toResponse it should return client")
    void whenRunToResponseItShouldReturnClient() {
        String cpf = "52350631044";
        String name = "John Smith";
        Client clientMock = mock(Client.class);
        when(clientMock.cpf()).thenReturn(cpf);
        when(clientMock.name()).thenReturn(name);
        when(this.addressResponseMapper.toResponse(null)).thenReturn(null);

        ClientResponse response = this.clientResponseMapper.toResponse(clientMock);

        assertNotNull(response);
        assertEquals(cpf, response.cpf());
        assertEquals(name, response.name());
        verify(addressResponseMapper, times(1)).toResponse(null);
    }

    @Test
    @DisplayName("When run toResponse with null client it should return null")
    void whenRunToDomainWithNullClientEntityItShouldReturnNull() {
        assertNull(this.clientResponseMapper.toResponse(null));
        verify(addressResponseMapper, times(0)).toResponse(any());
    }

}