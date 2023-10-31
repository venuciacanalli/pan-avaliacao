package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.ClientProductResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public record ClientProductResponseMapper() {

    public ClientProductResponse toResponse(ClientProduct clientProduct){
        if(clientProduct == null)
            return null;
        return new ClientProductResponse(clientProduct.id(), clientProduct.productId(), clientProduct.name(), clientProduct.startDate());
    }

    public List<ClientProductResponse> toResponse(List<ClientProduct> clientProducts){
        if(clientProducts == null || clientProducts.isEmpty())
            return Collections.emptyList();
        return clientProducts.stream().map(this::toResponse).toList();
    }
}
