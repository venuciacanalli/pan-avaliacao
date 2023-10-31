package br.com.venuciacanalli.pan.evaluation.application.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;

import java.util.Optional;

public interface IClientGateway {
    Optional<Client> findClientByCpf(String cpf);
}
