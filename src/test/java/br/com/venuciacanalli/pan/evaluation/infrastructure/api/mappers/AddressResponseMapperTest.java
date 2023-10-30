package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.AddressResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressResponseMapperTest {

    @InjectMocks
    private AddressResponseMapper addressResponseMapper;

    @Test
    @DisplayName("When run toResponse it should return address")
    void whenRunToResponseItShouldReturnAddress() {
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

        AddressResponse addressResponse = this.addressResponseMapper.toResponse(adressMock);
        assertNotNull(addressResponse);
        assertEquals(addressId, addressResponse.id());
        assertEquals(street, addressResponse.street());
        assertEquals(number, addressResponse.number());
        assertEquals(complement, addressResponse.complement());
        assertEquals(neighborhood, addressResponse.neighborhood());
        assertEquals(cep, addressResponse.cep());
        assertEquals(city, addressResponse.city());
        assertEquals(uf, addressResponse.uf());
    }

    @Test
    @DisplayName("When run toResponse with null address it should return null")
    void whenRunToDomainWithNullAddressItShouldReturnNull() {
        assertNull(this.addressResponseMapper.toResponse(null));

    }
}