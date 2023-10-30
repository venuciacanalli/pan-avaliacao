package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {

    @Autowired
    private AddressEntityMapper addressEntityMapper;

    public Client toDomain(ClientEntity clientEntity){
        if(clientEntity == null)
            return null;
        Address address = this.addressEntityMapper.toDomain(clientEntity.getAddress());
        return new Client(clientEntity.getCpf(), clientEntity.getName(), address);
    }
}
