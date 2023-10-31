package br.com.venuciacanalli.pan.evaluation.infrastructure.api.mappers;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos.CEPAddressResponse;
import org.springframework.stereotype.Component;

@Component
public class CEPAddressResponseMapper {

    public CEPAddressResponse toResponse(CEPAddress addresByCep) {
        if(addresByCep == null){
            return null;
        }
        return new CEPAddressResponse(addresByCep.street(), addresByCep.numberInterval(), addresByCep.neighborhood(), addresByCep.cep(), addresByCep.city(), addresByCep.uf());
    }
}
