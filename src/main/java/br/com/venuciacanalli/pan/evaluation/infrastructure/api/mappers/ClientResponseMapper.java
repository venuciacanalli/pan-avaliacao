package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientResponse;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseMapper {
    public ClientResponse toResponse(Client client) {
        if(client == null)
            throw new IllegalArgumentException("client can't be null");
        return new ClientResponse(client.cpf(), client.name());
    }
}