package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientEntityMapperTest {

    @InjectMocks
    private ClientEntityMapper clientEntityMapper;

    @Mock
    private AddressEntityMapper addressEntityMapper;

    @Test
    @DisplayName("When run toDomain it should return client")
    void whenRunToDomainItShouldReturnClient() {
        String cpf = "52350631044";
        String name = "John Smith";
        ClientEntity clientEntityMock = mock(ClientEntity.class);
        when(clientEntityMock.getCpf()).thenReturn(cpf);
        when(clientEntityMock.getName()).thenReturn(name);
        when(addressEntityMapper.toDomain(null)).thenReturn(null);

        Client client = this.clientEntityMapper.toDomain(clientEntityMock);

        assertNotNull(client);
        assertEquals(cpf, client.cpf());
        assertEquals(name, client.name());
        verify(addressEntityMapper, times(1)).toDomain(null);
    }

    @Test
    @DisplayName("When run toDomain with null client entity it should throw illegal argument exception")
    void whenRunToDomainWithNullClientEntityItShouldReturnNull() {
        assertNull(this.clientEntityMapper.toDomain(null));
    }
}