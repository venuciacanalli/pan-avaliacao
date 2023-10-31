package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetProductsByClientUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientProductResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.ClientProductResponseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClientProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<List<ClientProductResponse>> clientResponseJacksonTester;

    @MockBean
    private IGetProductsByClientUseCase getProductsByClientUseCaseMock;

    @MockBean
    private ClientProductResponseMapper clientProductResponseMapperMock;

    @Test
    @DisplayName("find products by user")
    void findProductsByUser() throws Exception{
        String cpf = "86109026093";
        UUID id = UUID.fromString("53684fc6-e1b4-4a44-8b9d-119143470974");
        UUID productId = UUID.fromString("73528897-5847-4cc8-8242-10b1ada62043");
        String name = "Credit Card";
        Date startDate = new Date();

        ClientProduct clientProduct = new ClientProduct(id, productId, name, startDate);
        List<ClientProduct> clientProducts = List.of(clientProduct);
        when(this.getProductsByClientUseCaseMock.run(cpf)).thenReturn(clientProducts);

        ClientProductResponse clientProductResponse = new ClientProductResponse(id, productId, name, startDate);
        List<ClientProductResponse> clientProductResponseList = List.of(clientProductResponse);
        when(this.clientProductResponseMapperMock.toResponse(clientProducts)).thenReturn(clientProductResponseList);

        var response = this.mvc.perform(get("/api/v1/client/86109026093/products"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        var expectedJson = clientResponseJacksonTester.write(clientProductResponseList).getJson();
        assertEquals(expectedJson, response.getContentAsString());

        verify(getProductsByClientUseCaseMock, times(1)).run(cpf);
        verify(clientProductResponseMapperMock, times(1)).toResponse(clientProducts);
    }

    @Test
    @DisplayName("should be thrown 404 error when cpf is invalid")
    void shouldBeThrown404ErrorWhenCpfIsInvalid() throws Exception {
        var response = this.mvc.perform(get("/client/products"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

}