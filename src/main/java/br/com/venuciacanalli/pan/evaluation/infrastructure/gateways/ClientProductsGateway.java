package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientProductsGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.ClientProductEntityMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories.IClientProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientProductsGateway implements IClientProductsGateway {

    @Autowired
    private IClientProductsRepository clientProductsRepository;

    @Autowired
    private ClientProductEntityMapper clientProductEntityMapper;

    @Override
    public List<ClientProduct> findByClientCpf(@Param("cpf") String cpf) {
        List<ClientProductEntity> productEntities = this.clientProductsRepository.findByClientCpf(cpf);
        return this.clientProductEntityMapper.toDomain(productEntities);
    }
}
