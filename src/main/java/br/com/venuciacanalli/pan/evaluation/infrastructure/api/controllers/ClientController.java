package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetClientByCpfUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.ClientResponseMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/client", produces = "application/json;charset=UTF-8")
public class ClientController {

    @Autowired
    private IGetClientByCpfUseCase getClientByCpfUseCase;

    @Autowired
    private ClientResponseMapper clientResponseMapper;

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClientResponse> findByCpf(@Valid @NotBlank @PathVariable("cpf") String cpf){
        Client client = this.getClientByCpfUseCase.run(cpf);
        ClientResponse clientResponse = clientResponseMapper.toResponse(client);
        return ResponseEntity.ok(clientResponse);
    }
}
