package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        Long addressId = 1L;
        String street = "Rua da Cantareira";
        String number = "306";
        String complement = "Bl. 8 Ap 203";
        String neighborhood = "Centro";
        String cep = "01024900";
        String city = "São Paulo";
        String uf = "SP";
        Address adressMock = mock(Address.class);
        when(adressMock.id()).thenReturn(addressId);
        when(adressMock.street()).thenReturn(street);
        when(adressMock.number()).thenReturn(number);
        when(adressMock.complement()).thenReturn(complement);
        when(adressMock.neighborhood()).thenReturn(neighborhood);
        when(adressMock.cep()).thenReturn(cep);
        when(adressMock.city()).thenReturn(city);
        when(adressMock.uf()).thenReturn(uf);
        Client clientMock = mock(Client.class);
        when(clientMock.cpf()).thenReturn(cpf);
        when(clientMock.name()).thenReturn(name);
        when(clientMock.address()).thenReturn(adressMock);
        when(clientGateway.findClientByCpf(cpf)).thenReturn(Optional.of(clientMock));

        Client client = this.getClientByCpfUseCase.run(cpf);

        verify(clientGateway, times(1)).findClientByCpf(cpf);
        assertNotNull(client);
        assertEquals(cpf, client.cpf());
        assertEquals(name, client.name());
        Address address = client.address();
        assertNotNull(address);
        assertEquals(addressId, address.id());
        assertEquals(street, address.street());
        assertEquals(number, address.number());
        assertEquals(complement, address.complement());
        assertEquals(neighborhood, address.neighborhood());
        assertEquals(cep, address.cep());
        assertEquals(city, address.city());
        assertEquals(uf, address.uf());
    }

    @Test
    @DisplayName("When run get client by cpf with null cpf it should throw EmptyArgumentException")
    void whenRunGetClientByCpfWithNullCpfItShouldThrowEmptyArgumentException() {
        Exception exception = assertThrows(EmptyArgumentException.class, () -> this.getClientByCpfUseCase.run(null));
        assertEquals("The argument cpf can't be null or empty.", exception.getMessage());
        verify(clientGateway, times(0)).findClientByCpf(any());
    }

    @Test
    @DisplayName("When run get client by cpf with blank cpf it should throw EmptyArgumentException")
    void whenRunGetClientByCpfWithBlankCpfItShouldThrowEmptyArgumentException() {
        Exception exception = assertThrows(EmptyArgumentException.class, () -> this.getClientByCpfUseCase.run(" "));
        assertEquals("The argument cpf can't be null or empty.", exception.getMessage());
        verify(clientGateway, times(0)).findClientByCpf(any());
    }

    @Test
    @DisplayName("When run get client by cpf it should throw ObjectWithAttributeNotFoundException")
    void whenRunGetClientByCpfWithoutSavedClientItShouldThrowsNotFoundException() {
        String cpf = "52350631044";
        when(clientGateway.findClientByCpf(cpf)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ObjectWithAttributeNotFoundException.class, () -> this.getClientByCpfUseCase.run(cpf));
        assertEquals("Client with cpf 52350631044 not found", exception.getMessage());
        verify(clientGateway, times(1)).findClientByCpf(cpf);
    }
}