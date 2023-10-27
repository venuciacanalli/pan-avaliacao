package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.EmptyArgumentException;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.ObjectWithAttributeNotFoundException;
import java.util.Optional;

public class GetClientByCpfUseCase implements IGetClientByCpfUseCase{

    private final IClientGateway clientGateway;

    public GetClientByCpfUseCase(final IClientGateway clientGateway){
        this.clientGateway = clientGateway;
    }

    @Override
    public Client run(String cpf)  {
        if(cpf == null || cpf.trim().isEmpty())
            throw new EmptyArgumentException("cpf");
        Optional<Client> client =  clientGateway.findUserByCpf(cpf);
        return client.orElseThrow(() -> new ObjectWithAttributeNotFoundException("Client", "cpf", cpf));
    }
}
