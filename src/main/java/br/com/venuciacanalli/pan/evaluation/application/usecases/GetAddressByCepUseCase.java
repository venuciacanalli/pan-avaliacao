package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IAddressHelperGateway;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;

import java.util.Optional;

public class GetAddressByCepUseCase implements IGetAddressByCepUseCase {

    private final IAddressHelperGateway iAddressHelperGateway;

    private final StringArgumentValidator stringArgumentValidator;

    public GetAddressByCepUseCase(final IAddressHelperGateway iAddressHelperGateway, StringArgumentValidator stringArgumentValidator){
        this.iAddressHelperGateway =  iAddressHelperGateway;
        this.stringArgumentValidator = stringArgumentValidator;
    }

    public CEPAddress run(String cep){
        this.stringArgumentValidator.validate("cep", cep);
        Optional<CEPAddress> cepAddress= this.iAddressHelperGateway.findAddressByCep(cep);
        return cepAddress.orElseThrow(() -> new ObjectWithAttributeNotFoundException("Address", "cep", cep));
    }
}
