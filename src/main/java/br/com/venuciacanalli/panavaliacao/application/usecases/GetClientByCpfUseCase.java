package br.com.venuciacanalli.panavaliacao.application.usecases;

import br.com.venuciacanalli.panavaliacao.application.gateways.IClientGateway;
import br.com.venuciacanalli.panavaliacao.domain.entities.Client;

public class GetClientByCpfUseCase implements IGetClientByCpfUseCase{

    private final IClientGateway clientGateway;

    public GetClientByCpfUseCase(final IClientGateway clientGateway){
        this.clientGateway = clientGateway;
    }

    @Override
    public Client run(String cpf) {
        if(cpf == null || cpf.trim().isEmpty())
            throw new IllegalArgumentException();
        return clientGateway.findUserByCpf(cpf);
    }
}
