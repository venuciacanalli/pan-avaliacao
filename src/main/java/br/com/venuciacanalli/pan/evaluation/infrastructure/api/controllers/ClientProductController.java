package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetProductsByClientUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientProductResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.ClientProductResponseMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/client/{cpf}/products", produces = "application/json;charset=UTF-8")
public class ClientProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IGetProductsByClientUseCase getProductsByClientUseCase;

    @Autowired
    private ClientProductResponseMapper clientProductResponseMapper;
    
    @GetMapping
    public ResponseEntity<List<ClientProductResponse>> findProductsByUser(@Valid @NotBlank @PathVariable("cpf") String cpf){
        LOGGER.info(String.format("start find products by client (%s)", cpf));
        List<ClientProduct> clientProducts = this.getProductsByClientUseCase.run(cpf);
        List<ClientProductResponse> clientProductResponseList = clientProductResponseMapper.toResponse(clientProducts);
        LOGGER.info(String.format("sucess in find products by client (%s) : %s ", cpf, clientProductResponseList));
        return ResponseEntity.ok(clientProductResponseList);
    }
}
