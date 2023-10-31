package br.com.venuciacanalli.pan.evaluation.application.validators;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;

public class ClientExistsValidator {

    private final IClientGateway clientGateway;

    public ClientExistsValidator(final IClientGateway clientGateway){
        this.clientGateway = clientGateway;
    }

    public void validate(String cpf){
        if(!clientGateway.existsByCpf(cpf))
            throw new ObjectWithAttributeNotFoundException("Client", "cpf", cpf);
    }
}
