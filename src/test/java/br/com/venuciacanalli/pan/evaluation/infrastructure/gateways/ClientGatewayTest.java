package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.ClientEntityMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories.IClientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClientGatewayTest {

    @InjectMocks
    private ClientGateway clientGateway;

    @Mock
    private IClientRepository clientRepository;

    @Mock
    private ClientEntityMapper clientEntityMapper;

    @Test
    @DisplayName("When find client by cpf it should return client")
    void whenFindClientByCpfItShouldReturnClient() {
        String cpf = "52350631044";
        ClientEntity clientEntityMock = mock(ClientEntity.class);
        Client clientMock = mock(Client.class);
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.of(clientEntityMock));
        when(clientEntityMapper.toDomain(clientEntityMock)).thenReturn(clientMock);

        Optional<Client> client = this.clientGateway.findClientByCpf(cpf);

        assertTrue(client.isPresent());
        assertEquals(clientMock, client.get());
        verify(clientRepository, times(1)).findByCpf(cpf);
        verify(clientEntityMapper, times(1)).toDomain(clientEntityMock);
    }

    @Test
    @DisplayName("When there is no customer with the CPF entered, it returns null")
    void whenThereIsNotCustomerWithTheCPFEnteredItReturnsNull() {
        String cpf = "52350631044";
        when(clientRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        Optional<Client> client = this.clientGateway.findClientByCpf(cpf);

        assertTrue(client.isEmpty());
        verify(clientRepository, times(1)).findByCpf(cpf);
        verify(clientEntityMapper, times(0)).toDomain(any());
    }
}