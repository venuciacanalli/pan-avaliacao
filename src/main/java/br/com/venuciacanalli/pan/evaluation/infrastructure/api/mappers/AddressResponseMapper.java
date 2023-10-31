package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.AddressResponse;
import org.springframework.stereotype.Component;

@Component
public class AddressResponseMapper {

    public AddressResponse toResponse(Address address){
        if(address == null)
            return null;
        return new AddressResponse(address.id(), address.street(), address.number(), address.complement(),
                address.neighborhood(), address.cep(), address.city(), address.uf());
    }
}