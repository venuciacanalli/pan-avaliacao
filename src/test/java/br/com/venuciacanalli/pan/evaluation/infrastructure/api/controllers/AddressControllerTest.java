package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetAddressByCepUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.CEPAddressResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.CEPAddressResponseMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AddressControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<CEPAddressResponse> cepAddressResponseJacksonTester;

    @MockBean
    private IGetAddressByCepUseCase getAddressByCepUseCase;

    @MockBean
    private CEPAddressResponseMapper cepAddressResponseMapper;

    @Test
    @DisplayName("find by cpf")
    void findByCep() throws Exception {
        CEPAddress cepAddressMock = mock(CEPAddress.class);
        CEPAddressResponse cepAddressResponse = new CEPAddressResponse("Rua da Cantareira", "100-3600",  "Centro", "01024900", "São Paulo", "SP");
        when(getAddressByCepUseCase.run("01024900")).thenReturn(cepAddressMock);
        when(cepAddressResponseMapper.toResponse(cepAddressMock)).thenReturn(cepAddressResponse);

        var response = this.mvc.perform(get("/api/v1/address/01024900"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        var expectedJson = cepAddressResponseJacksonTester.write(cepAddressResponse).getJson();
        assertEquals(expectedJson, response.getContentAsString());

    }
}