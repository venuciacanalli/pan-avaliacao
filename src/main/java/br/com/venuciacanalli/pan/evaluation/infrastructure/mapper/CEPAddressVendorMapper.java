package br.com.venuciacanalli.pan.evaluation.infrastructure.mapper;


import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.CEPAddressVendor;
import org.springframework.stereotype.Component;

@Component
public class CEPAddressVendorMapper {

    public CEPAddress toDomain(CEPAddressVendor vendor){
        if(vendor == null)
            return null;
        return new CEPAddress(vendor.logradouro(), vendor.complemento(), vendor.bairro(), vendor.cep(), vendor.localidade(), vendor.uf());
    }
}
