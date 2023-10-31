package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientProductEntityMapperTest {

    @InjectMocks
    private ClientProductEntityMapper clientProductEntityMapper;

    @Test
    void whenRunToDomainItShoulReturnClientProduct() {
        UUID productId = UUID.fromString("00dc3bc9-a36e-473c-8bad-622aea59e3df");
        String productName = "Credit Card";
        UUID id = UUID.fromString("957dda6e-785a-408b-be48-958db8957d18");
        Date startDate = new Date();

        ProductEntity productEntityMock = mock(ProductEntity.class);
        when(productEntityMock.getId()).thenReturn(productId);
        when(productEntityMock.getName()).thenReturn(productName);

        ClientProductEntity clientProductEntityMock = mock(ClientProductEntity.class);
        when(clientProductEntityMock.getId()).thenReturn(id);
        when(clientProductEntityMock.getProduct()).thenReturn(productEntityMock);
        when(clientProductEntityMock.getStartDate()).thenReturn(startDate);

        ClientProduct domain = this.clientProductEntityMapper.toDomain(clientProductEntityMock);

        assertNotNull(domain);
        assertEquals(id, domain.id());
        assertEquals(productId, domain.productId());
        assertEquals(productName, domain.name());
        assertEquals(startDate, domain.startDate());
    }

    @Test
    @DisplayName("When run toDomain with null client entity it should return null")
    void whenRunToDomainWithNullClientProductEntityItShouldReturnNull() {
        assertNull(this.clientProductEntityMapper.toDomain((ClientProductEntity) null));
    }

    @Test
    void whenRunToDomainForEntityListItShoulReturnClientProductList() {
        UUID productId = UUID.fromString("00dc3bc9-a36e-473c-8bad-622aea59e3df");
        String productName = "Credit Card";
        UUID id = UUID.fromString("957dda6e-785a-408b-be48-958db8957d18");
        Date startDate = new Date();

        ProductEntity productEntityMock = mock(ProductEntity.class);
        when(productEntityMock.getId()).thenReturn(productId);
        when(productEntityMock.getName()).thenReturn(productName);

        ClientProductEntity clientProductEntityMock = mock(ClientProductEntity.class);
        when(clientProductEntityMock.getId()).thenReturn(id);
        when(clientProductEntityMock.getProduct()).thenReturn(productEntityMock);
        when(clientProductEntityMock.getStartDate()).thenReturn(startDate);

        List<ClientProduct> clientProductsDomain = this.clientProductEntityMapper.toDomain(Arrays.asList(clientProductEntityMock));

        assertNotNull(clientProductsDomain);
        assertEquals(1, clientProductsDomain.size());

        ClientProduct domain = clientProductsDomain.get(0);
        assertNotNull(domain);
        assertEquals(id, domain.id());
        assertEquals(productId, domain.productId());
        assertEquals(productName, domain.name());
        assertEquals(startDate, domain.startDate());
    }


    @Test
    @DisplayName("When run toDomain with null client entity list it should return empty list")
    void whenRunToDomainWithNullClientProductEntityListItShouldReturnEmptyList() {
        List<ClientProduct> domains = this.clientProductEntityMapper.toDomain((List<ClientProductEntity>) null);
        assertNotNull(domains);
        assertTrue(domains.isEmpty());
    }

}