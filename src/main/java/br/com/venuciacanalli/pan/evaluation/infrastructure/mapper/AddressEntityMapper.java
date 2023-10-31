package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Address;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressEntityMapper {
    public Address toDomain(AddressEntity addressEntity) {
        if(addressEntity == null)
            return null;
        return new Address(addressEntity.getId(),
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getComplement(),
                addressEntity.getNeighborhood(),
                addressEntity.getCep(),
                addressEntity.getCity(),
                addressEntity.getUf());
    }
}