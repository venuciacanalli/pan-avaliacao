package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientProductResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientProductResponseMapperTest {

    @InjectMocks
    private ClientProductResponseMapper clientProductResponseMapper;

    @Test
    @DisplayName("When run toResponse it should return client product")
    void whenRunToResponseItShouldReturnClientProduct() {
        UUID id = UUID.fromString("53684fc6-e1b4-4a44-8b9d-119143470974");
        UUID productId = UUID.fromString("73528897-5847-4cc8-8242-10b1ada62043");
        String name = "Credit Card";
        Date startDate = new Date();
        ClientProduct clientProductMock = mock(ClientProduct.class);
        when(clientProductMock.id()).thenReturn(id);
        when(clientProductMock.productId()).thenReturn(productId);
        when(clientProductMock.name()).thenReturn(name);
        when(clientProductMock.startDate()).thenReturn(startDate);

        ClientProductResponse response = this.clientProductResponseMapper.toResponse(clientProductMock);

        assertNotNull(response);
        assertEquals(id, response.id());
        assertEquals(productId, response.productId());
        assertEquals(name, response.name());
        assertEquals(startDate, response.startDate());

    }

    @Test
    @DisplayName("When run toResponse it should return client product list")
    void whenRunToResponseItShouldReturnClientProductList() {
        UUID id = UUID.fromString("53684fc6-e1b4-4a44-8b9d-119143470974");
        UUID productId = UUID.fromString("73528897-5847-4cc8-8242-10b1ada62043");
        String name = "Credit Card";
        Date startDate = new Date();
        ClientProduct clientProductMock = mock(ClientProduct.class);
        when(clientProductMock.id()).thenReturn(id);
        when(clientProductMock.productId()).thenReturn(productId);
        when(clientProductMock.name()).thenReturn(name);
        when(clientProductMock.startDate()).thenReturn(startDate);

        List<ClientProductResponse> responseList = this.clientProductResponseMapper.toResponse(List.of(clientProductMock));

        assertNotNull(responseList);
        assertEquals(1, responseList.size());

        ClientProductResponse response = responseList.get(0);
        assertEquals(id, response.id());
        assertEquals(productId, response.productId());
        assertEquals(name, response.name());
        assertEquals(startDate, response.startDate());
    }

    @Test
    @DisplayName("When run toResponse with null client product it should return null")
    void whenRunToDomainWithNullClientProductItShouldReturnNull() {
        assertNull(this.clientProductResponseMapper.toResponse((ClientProduct) null));
    }

    @Test
    @DisplayName("When run toResponse with null client product it should return empty list")
    void whenRunToDomainWithNullClientProductListItShouldReturnNull() {
        List<ClientProductResponse> response = this.clientProductResponseMapper.toResponse((List<ClientProduct>) null);
        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}