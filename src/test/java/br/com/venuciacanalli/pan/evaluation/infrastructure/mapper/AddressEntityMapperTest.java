package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.AddressEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressEntityMapperTest {

    @InjectMocks
    private AddressEntityMapper addressEntityMapper;

    @Test
    @DisplayName("When run toDomain it should return address")
    void whenRunToDomainItShouldReturnAddress() {
        Long id = 1L;
        String street = "Rua da Cantareira";
        String number = "306";
        String complement = "Bl. 8 Ap 203";
        String neighborhood = "Centro";
        String cep = "01024900";
        String city = "São Paulo";
        String uf = "SP";
        AddressEntity addressEntityMock = mock(AddressEntity.class);
        when(addressEntityMock.getId()).thenReturn(id);
        when(addressEntityMock.getStreet()).thenReturn(street);
        when(addressEntityMock.getNumber()).thenReturn(number);
        when(addressEntityMock.getComplement()).thenReturn(complement);
        when(addressEntityMock.getNeighborhood()).thenReturn(neighborhood);
        when(addressEntityMock.getCep()).thenReturn(cep);
        when(addressEntityMock.getCity()).thenReturn(city);
        when(addressEntityMock.getUf()).thenReturn(uf);

        Address address = this.addressEntityMapper.toDomain(addressEntityMock);

        assertNotNull(address);
        assertEquals(id, address.id());
        assertEquals(street, address.street());
        assertEquals(number, address.number());
        assertEquals(complement, address.complement());
        assertEquals(neighborhood, address.neighborhood());
        assertEquals(cep, address.cep());
        assertEquals(city, address.city());
        assertEquals(uf, address.uf());
    }

    @Test
    @DisplayName("When run toDomain with null client entity it should return null")
    void whenRunToDomainWithNullClientEntityItShouldReturnNull() {
        assertNull(this.addressEntityMapper.toDomain(null));
    }
}