package br.com.venuciacanalli.pan.evaluation.application.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;

import java.util.List;

public interface IClientProductsGateway {
    List<ClientProduct> findByClientCpf(String cpf);
}
