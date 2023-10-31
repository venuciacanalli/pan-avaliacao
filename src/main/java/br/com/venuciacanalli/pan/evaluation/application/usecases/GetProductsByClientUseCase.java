package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientProductsGateway;
import br.com.venuciacanalli.pan.evaluation.application.validators.ClientExistsValidator;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;

import java.util.List;

public class GetProductsByClientUseCase implements IGetProductsByClientUseCase{

    private final IClientProductsGateway clientProductsGateway;

    private final StringArgumentValidator stringArgumentValidator;

    private final ClientExistsValidator clientExistsValidator;

    public GetProductsByClientUseCase(final IClientProductsGateway clientProductsGateway,
                                      final StringArgumentValidator stringArgumentValidator,
                                      final ClientExistsValidator clientExistsValidator){
        this.clientProductsGateway = clientProductsGateway;
        this.stringArgumentValidator = stringArgumentValidator;
        this.clientExistsValidator = clientExistsValidator;
    }

    @Override
    public List<ClientProduct> run(String cpf) {
        this.stringArgumentValidator.validate("cpf", cpf);
        this.clientExistsValidator.validate(cpf);
        return this.clientProductsGateway.findByClientCpf(cpf);
    }
}
