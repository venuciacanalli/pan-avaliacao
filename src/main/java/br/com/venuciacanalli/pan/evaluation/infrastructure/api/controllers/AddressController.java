package br.com.venuciacanalli.pan.evaluation.infrastructure.api.controllers;

import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetAddressByCepUseCase;
import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.CEPAddressResponse;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers.CEPAddressResponseMapper;
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

@RestController
@RequestMapping(value = "/api/v1/address", produces = "application/json;charset=UTF-8")
public class AddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private IGetAddressByCepUseCase getAddressByCepUseCase;

    @Autowired
    private CEPAddressResponseMapper cepAddressResponseMapper;

    @GetMapping("/{cep}")
    public ResponseEntity<CEPAddressResponse> findByCep(@Valid @NotBlank @PathVariable("cep") String cep){
        LOGGER.info(String.format("start find by cep (%s)", cep));
        CEPAddress addresByCep = this.getAddressByCepUseCase.run(cep);
        CEPAddressResponse cepAddressResponse = cepAddressResponseMapper.toResponse(addresByCep);
        LOGGER.info(String.format("success in find by cep (%s) : %s ", cep, cepAddressResponse));
        return ResponseEntity.ok(cepAddressResponse);
    }
}
