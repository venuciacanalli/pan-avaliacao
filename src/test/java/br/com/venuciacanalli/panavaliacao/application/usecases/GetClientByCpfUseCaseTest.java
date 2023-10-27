package br.com.venuciacanalli.panavaliacao.application.usecases;

import br.com.venuciacanalli.panavaliacao.application.gateways.IClientGateway;
import br.com.venuciacanalli.panavaliacao.domain.entities.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetClientByCpfUseCaseTest {

    @InjectMocks
    private GetClientByCpfUseCase getClientByCpfUseCase;

    @Mock
    private IClientGateway clientGateway;

    @Test
    @DisplayName("When run get client by cpf it should return client")
    void whenRunGetClientByCpfItShouldReturnClient() {
        String cpf = "52350631044";
        String name = "John Smith";
        Client clientMock = mock(Client.class);
        when(clientMock.cpf()).thenReturn(cpf);
        when(clientMock.name()).thenReturn(name);
        when(clientGateway.findUserByCpf(cpf)).thenReturn(clientMock);

        Client client = this.getClientByCpfUseCase.run(cpf);

        verify(clientGateway, times(1)).findUserByCpf(cpf);
        assertNotNull(client);
        assertEquals(cpf, client.cpf());
        assertEquals(name, client.name());
    }
}