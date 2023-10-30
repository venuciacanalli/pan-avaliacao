package br.com.venuciacanalli.panavaliacao.application.gateways;

import br.com.venuciacanalli.panavaliacao.domain.entities.Client;

public interface IClientGateway {
    Client findUserByCpf(String cpf);
}
