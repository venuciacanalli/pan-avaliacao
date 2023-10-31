package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.CEPAddressVendor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CEPAddressVendorMapperTest {
    @InjectMocks
    private CEPAddressVendorMapper cepAddressVendorMapper;

    @Test
    @DisplayName("When run toDomain it should return address")
    void whenRunToDomainItShouldReturnAddress() {
        String cep = "01024900";
        String street = "Rua da Cantareira";
        String numberInterval = "1-1000";
        String neighborhood = "Centro";
        String city = "São Paulo";
        String uf = "SP";

        CEPAddressVendor vendorMock = mock(CEPAddressVendor.class);
        when(vendorMock.cep()).thenReturn(cep);
        when(vendorMock.logradouro()).thenReturn(street);
        when(vendorMock.complemento()).thenReturn(numberInterval);
        when(vendorMock.bairro()).thenReturn(neighborhood);
        when(vendorMock.localidade()).thenReturn(city);
        when(vendorMock.uf()).thenReturn(uf);

        CEPAddress domain = this.cepAddressVendorMapper.toDomain(vendorMock);

        assertNotNull(domain);
        assertEquals(cep, domain.cep());
        assertEquals(street, domain.street());
        assertEquals(numberInterval, domain.numberInterval());
        assertEquals(neighborhood, domain.neighborhood());
        assertEquals(city, domain.city());
        assertEquals(uf, domain.uf());
    }

    @Test
    @DisplayName("When run toDomain with null address it should return null")
    void whenRunToDomainWithNullAddressItShouldReturnNull() {
        assertNull(this.cepAddressVendorMapper.toDomain(null));
    }
}