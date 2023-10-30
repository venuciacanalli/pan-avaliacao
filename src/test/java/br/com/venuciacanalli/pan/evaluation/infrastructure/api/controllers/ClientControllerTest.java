package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetClientByCpfUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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
        ClientResponse clientResponse = new ClientResponse( "86109026093", "John Smith");
        when(getClientByCpfUseCase.run("86109026093")).thenReturn(clientMock);
        when(clientResponseMapper.toResponse(clientMock)).thenReturn(clientResponse);
        var response = this.mvc.perform(get("/api/v1/client/86109026093"))
                .andReturn().getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        var expectedJson = clientResponseJacksonTester.write(
                new ClientResponse("86109026093", "John Smith")
        ).getJson();
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