package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IAddressHelperGateway;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
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
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class GetAddressByCepUseCaseTest {

    @InjectMocks
    private GetAddressByCepUseCase getAddressByCepUseCase;

    @Mock
    private IAddressHelperGateway iAddressHelperGateway;

    @Mock
    private StringArgumentValidator stringArgumentValidator;

    @Test
    @DisplayName("When run get address by cep it should return address")
    void whenRunGetAddressByCepItShouldReturnAddress() {
        String cep = "01024900";
        String street = "Rua da Cantareira";
        String numberInteval = "0-1000";
        String neighborhood = "Centro";
        String city = "São Paulo";
        String uf = "SP";

        CEPAddress addressMock = mock(CEPAddress.class);
        when(addressMock.cep()).thenReturn(cep);
        when(addressMock.street()).thenReturn(street);
        when(addressMock.numberInterval()).thenReturn(numberInteval);
        when(addressMock.neighborhood()).thenReturn(neighborhood);
        when(addressMock.city()).thenReturn(city);
        when(addressMock.uf()).thenReturn(uf);

        when(iAddressHelperGateway.findAddressByCep(cep)).thenReturn(Optional.of(addressMock));

        CEPAddress returnedAddress = this.getAddressByCepUseCase.run(cep);

        verify(stringArgumentValidator, times(1)).validate("cep", cep);
        verify(iAddressHelperGateway, times(1)).findAddressByCep(cep);
        assertNotNull(returnedAddress);
        assertEquals(street, returnedAddress.street());
        assertEquals(numberInteval, returnedAddress.numberInterval());
        assertEquals(neighborhood, returnedAddress.neighborhood());
        assertEquals(cep, returnedAddress.cep());
        assertEquals(city, returnedAddress.city());
        assertEquals(uf, returnedAddress.uf());
    }

    @Test
    @DisplayName("When run get address by cep with null cep it should throw EmptyArgumentException")
    void whenRunGetAddressByCepWithNullCepItShouldThrowEmptyArgumentException() {
        doThrow(EmptyArgumentException.class).when(stringArgumentValidator).validate("cep", null);
        assertThrows(EmptyArgumentException.class, () -> this.getAddressByCepUseCase.run(null));
        verify(iAddressHelperGateway, times(0)).findAddressByCep(any());
    }

    @Test
    @DisplayName("When run get address by cep that no exists it should throw ObjectWithAttributeNotFoundException")
    void whenRunGetAddressByCepThatNoExistItShouldThrowsNotFoundException() {
        String cep = "01024900";
        when(iAddressHelperGateway.findAddressByCep(cep)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ObjectWithAttributeNotFoundException.class, () -> this.getAddressByCepUseCase.run(cep));
        assertEquals("Address with cep 01024900 not found", exception.getMessage());
        verify(iAddressHelperGateway, times(1)).findAddressByCep(cep);
    }
}