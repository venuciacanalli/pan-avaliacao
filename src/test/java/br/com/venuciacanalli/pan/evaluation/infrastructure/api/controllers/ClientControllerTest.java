package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetClientByCpfUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.AddressResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.ClientResponseMapper;
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

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ClientResponse> clientResponseJacksonTester;

    @MockBean
    private IGetClientByCpfUseCase getClientByCpfUseCase;

    @MockBean
    private ClientResponseMapper clientResponseMapper;
    @Test
    @DisplayName("find by cpf")
    void findByCpf() throws Exception {
        Client clientMock = mock(Client.class);
        AddressResponse addressResponse = new AddressResponse(UUID.fromString("4cc493ca-6561-4e55-8a1e-aa527d9034f2"), "Rua da Cantareira", "306", "Bl. 8 Ap 203", "Centro","01024900", "São Paulo", "SP");
        ClientResponse clientResponse = new ClientResponse( "86109026093", "John Smith", addressResponse);
        when(getClientByCpfUseCase.run("86109026093")).thenReturn(clientMock);
        when(clientResponseMapper.toResponse(clientMock)).thenReturn(clientResponse);
        var response = this.mvc.perform(get("/api/v1/client/86109026093"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        var expectedJson = clientResponseJacksonTester.write(clientResponse).getJson();
        assertEquals(expectedJson, response.getContentAsString());
    }

    @Test
    @DisplayName("should be thrown 404 error when cpf is invalid")
    void shouldBeThrown404ErrorWhenCpfIsInvalid() throws Exception {
        var response = this.mvc.perform(post("/client/86109026093"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}