package br.com.venuciacanalli.pan.evaluation.infrastructure.config;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IClientGateway;
import br.com.venuciacanalli.pan.evaluation.application.usecases.GetClientByCpfUseCase;
import br.com.venuciacanalli.pan.evaluation.application.usecases.IGetClientByCpfUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    IGetClientByCpfUseCase getClientByCpfUseCase(IClientGateway clientGateway){
        return new GetClientByCpfUseCase(clientGateway);
    }
}
