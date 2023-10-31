package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientProductsGateway;
import br.com.venuciacanalli.pan.evaluation.application.validators.ClientExistsValidator;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
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
class GetProductsByClientUseCaseTest {

    @InjectMocks
    private GetProductsByClientUseCase getProductsByClientUseCase;

    @Mock
    private IClientProductsGateway clientProductsGatewayMock;

    @Mock
    private StringArgumentValidator stringArgumentValidatorMock;

    @Mock
    private ClientExistsValidator clientExistsValidatorMock;

    @Test
    @DisplayName("When run get products by client it should return products list")
    void whenRunGetProductsByClientItShouldReturnProductsList() {
        String cpf = "52350631044";
        ClientProduct clientProductMock1 = mock(ClientProduct.class);
        ClientProduct clientProductMock2 = mock(ClientProduct.class);
        List<ClientProduct> clientProductsMock = Arrays.asList(clientProductMock1, clientProductMock2);
        when(clientProductsGatewayMock.findByClientCpf(cpf)).thenReturn(clientProductsMock);

        List< ClientProduct> clientProducts = this.getProductsByClientUseCase.run(cpf);
        assertNotNull(clientProductsMock);
        assertEquals(2, clientProducts.size());
        verify(this.stringArgumentValidatorMock, times(1)).validate("cpf", cpf);
        verify(this.clientExistsValidatorMock, times(1)).validate( cpf);
        verify(this.clientProductsGatewayMock, times(1)).findByClientCpf(cpf);
    }

    @Test
    @DisplayName("When run get products by client with null cpf it should throw EmptyArgumentException")
    void whenRunGetProductsByClientWithNullCpfItShouldThrowEmptyArgumentException() {
        doThrow(EmptyArgumentException.class).when(this.stringArgumentValidatorMock).validate("cpf", null);
        assertThrows(EmptyArgumentException.class, () -> this.getProductsByClientUseCase.run(null));
        verify(this.clientProductsGatewayMock, times(0)).findByClientCpf(any());
    }

    @Test
    @DisplayName("When run get products by client with null cpf it should throw ObjectWithAttributeNotFoundException")
    void whenRunGetProductsByClientWithNonExistentCpfItShouldThrowEmptyArgumentException() {
        doThrow(ObjectWithAttributeNotFoundException.class).when(this.clientExistsValidatorMock).validate("52350631044");
        assertThrows(ObjectWithAttributeNotFoundException.class, () -> this.getProductsByClientUseCase.run("52350631044"));
        verify(this.clientProductsGatewayMock, times(0)).findByClientCpf(any());
    }

}