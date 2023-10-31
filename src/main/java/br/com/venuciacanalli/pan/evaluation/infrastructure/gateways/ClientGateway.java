package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.ClientEntityMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientGateway implements IClientGateway {

    @Autowired
    private IClientRepository clientRepository;

    @Autowired
    private ClientEntityMapper clientEntityMapper;

    @Override
    @Cacheable(value="clients", key="#cpf")
    public Optional<Client> findClientByCpf(String cpf) {
        Optional<ClientEntity> optClientEntity = this.clientRepository.findByCpf(cpf);
        return optClientEntity.map(clientEntity -> clientEntityMapper.toDomain(clientEntity));
    }
}
