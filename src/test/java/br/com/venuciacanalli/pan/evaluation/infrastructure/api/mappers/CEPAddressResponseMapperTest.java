package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.CEPAddressResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CEPAddressResponseMapperTest {

    @InjectMocks
    private CEPAddressResponseMapper cepAddressResponseMapper;

    @Test
    @DisplayName("When run toResponse it should return address")
    void whenRunToResponseItShouldReturnAddress() {
        String cep = "01024900";
        String street = "Rua da Cantareira";
        String numberInterval = "0-1000";
        String neighborhood = "Centro";
        String city = "São Paulo";
        String uf = "SP";


        CEPAddress domain = mock(CEPAddress.class);
        when(domain.cep()).thenReturn(cep);
        when(domain.street()).thenReturn(street);
        when(domain.numberInterval()).thenReturn(numberInterval);
        when(domain.neighborhood()).thenReturn(neighborhood);
        when(domain.city()).thenReturn(city);
        when(domain.uf()).thenReturn(uf);

        CEPAddressResponse response = this.cepAddressResponseMapper.toResponse(domain);

        assertNotNull(response);
        assertEquals(cep, response.cep());
        assertEquals(street, response.street());
        assertEquals(numberInterval, response.numberInterval());
        assertEquals(neighborhood, response.neighborhood());
        assertEquals(city, response.city());
        assertEquals(uf, response.uf());
    }

    @Test
    @DisplayName("When run toResponse with null address it should return null")
    void whenRunToDomainWithNullAddressItShouldReturnNull() {
        assertNull(this.cepAddressResponseMapper.toResponse(null));

    }
}