package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper {

    @Autowired
    private AddressResponseMapper addressResponseMapper;
    public ClientResponse toResponse(Client client) {
        if(client == null)
            return null;
        return new ClientResponse(client.cpf(), client.name(),
                this.addressResponseMapper.toResponse(client.address()));
    }
}