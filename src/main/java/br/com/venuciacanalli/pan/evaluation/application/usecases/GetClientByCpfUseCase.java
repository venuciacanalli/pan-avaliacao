package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import java.util.Optional;

public class GetClientByCpfUseCase implements IGetClientByCpfUseCase{

    private final IClientGateway clientGateway;

    private final StringArgumentValidator stringArgumentValidator;

    public GetClientByCpfUseCase(final IClientGateway clientGateway, final StringArgumentValidator stringArgumentValidator){
        this.clientGateway = clientGateway;
        this.stringArgumentValidator = stringArgumentValidator;
    }

    @Override
    public Client run(String cpf)  {
        this.stringArgumentValidator.validate("cpf", cpf);
        Optional<Client> client =  clientGateway.findClientByCpf(cpf);
        return client.orElseThrow(() -> new ObjectWithAttributeNotFoundException("Client", "cpf", cpf));
    }
}
