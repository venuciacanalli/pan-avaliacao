package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ClientProductEntityMapper {

    public List<ClientProduct> toDomain(List<ClientProductEntity> entities){
        if(entities == null || entities.isEmpty())
            return Collections.emptyList();
        return entities.stream().map(this::toDomain).toList();
    }

    public ClientProduct toDomain(ClientProductEntity clientProductEntity){
        if(clientProductEntity == null)
            return null;
        return new ClientProduct(clientProductEntity.getId(), clientProductEntity.getProduct().getId(), clientProductEntity.getProduct().getName(), clientProductEntity.getStartDate());
    }
}