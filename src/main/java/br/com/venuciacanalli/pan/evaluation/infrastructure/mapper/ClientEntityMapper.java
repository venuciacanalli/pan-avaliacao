package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {
    public Client toDomain(ClientEntity clientEntity){
        if(clientEntity == null)
            throw new IllegalArgumentException("clientEntity can't be null");
        return new Client(clientEntity.getCpf(), clientEntity.getName());
    }
}
