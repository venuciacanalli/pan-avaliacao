package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.ClientProductEntityMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories.IClientProductsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ClientProductsGatewayTest {

    @InjectMocks
    private ClientProductsGateway clientProductsGateway;

    @Mock
    private IClientProductsRepository clientProductsRepositoryMock;

    @Mock
    private ClientProductEntityMapper clientProductEntityMapperMock;

    @Test
    @DisplayName("When find products by client it should return product list")
    void whenFindClientByCpfItShouldReturnClient() {
        String cpf = "52350631044";
        ClientProductEntity clientProductEntityMock = mock(ClientProductEntity.class);
        List<ClientProductEntity> clientProductsEntitiesMock = Arrays.asList(clientProductEntityMock);
        ClientProduct clientProductMock = mock(ClientProduct.class);
        List<ClientProduct> clientProductsMock = Arrays.asList(clientProductMock);
        when(this.clientProductsRepositoryMock.findByClientCpf(cpf)).thenReturn(clientProductsEntitiesMock);
        when(this.clientProductEntityMapperMock.toDomain(clientProductsEntitiesMock)).thenReturn(clientProductsMock);

        List<ClientProduct> clientProduts = this.clientProductsGateway.findByClientCpf(cpf);

        assertNotNull(clientProduts);
        assertEquals(1, clientProduts.size());
        verify(clientProductsRepositoryMock, times(1)).findByClientCpf(cpf);
        verify(clientProductEntityMapperMock, times(1)).toDomain(clientProductsEntitiesMock);
    }

}