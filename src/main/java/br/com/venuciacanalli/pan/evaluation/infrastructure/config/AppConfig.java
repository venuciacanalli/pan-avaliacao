package br.com.venuciacanalli.pan.evaluation.infrastructure.config;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IAddressHelperGateway;
import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientProductsGateway;
import br.com.venuciacanalli.pan.evaluation.application.usecases.*;
import br.com.venuciacanalli.pan.evaluation.application.validators.ClientExistsValidator;
import br.com.venuciacanalli.pan.evaluation.application.validators.StringArgumentValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    StringArgumentValidator getStringArgumentValidator(){
        return new StringArgumentValidator();
    }

    @Bean
    ClientExistsValidator getClientExistsValidator(final IClientGateway clientGateway){
        return new ClientExistsValidator(clientGateway);
    }

    @Bean
    IGetClientByCpfUseCase getClientByCpfUseCase(final IClientGateway clientGateway, final StringArgumentValidator stringArgumentValidator){
        return new GetClientByCpfUseCase(clientGateway, stringArgumentValidator);
    }

    @Bean
    IGetProductsByClientUseCase getProductsByClientUseCase(final IClientProductsGateway clientProductsGateway, final StringArgumentValidator stringArgumentValidator, final ClientExistsValidator clientExistsValidator){
        return new GetProductsByClientUseCase(clientProductsGateway, stringArgumentValidator, clientExistsValidator);
    }
    
    @Bean
    IGetAddressByCepUseCase getAddressByCepUseCase(final IAddressHelperGateway addressHelperGateway, final StringArgumentValidator stringArgumentValidator){
        return new GetAddressByCepUseCase(addressHelperGateway, stringArgumentValidator);
    }
}
