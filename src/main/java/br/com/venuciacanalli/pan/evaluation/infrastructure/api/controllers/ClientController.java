package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetClientByCpfUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.ClientResponseMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/client", produces = "application/json;charset=UTF-8")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IGetClientByCpfUseCase getClientByCpfUseCase;

    @Autowired
    private ClientResponseMapper clientResponseMapper;

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponse> findByCpf(@Valid @NotBlank @PathVariable("cpf") String cpf){
        LOGGER.info(String.format("start find by cpf (%s)", cpf));
        Client client = this.getClientByCpfUseCase.run(cpf);
        ClientResponse clientResponse = clientResponseMapper.toResponse(client);
        LOGGER.info(String.format("sucess in find by cpf (%s) : %s ", cpf, clientResponse));
        return ResponseEntity.ok(clientResponse);
    }
}
