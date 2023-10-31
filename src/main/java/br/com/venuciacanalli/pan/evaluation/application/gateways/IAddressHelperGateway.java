package br.com.venuciacanalli.pan.evaluation.application.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;

import java.util.Optional;

public interface IAddressHelperGateway {

    public Optional<CEPAddress> findAddressByCep(String cep);
}
