package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientProductsGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientProductsGateway implements IClientProductsGateway {
    @Override
    public List<ClientProduct> findByClientCpf(String cpf) {
        return null;
    }
}
