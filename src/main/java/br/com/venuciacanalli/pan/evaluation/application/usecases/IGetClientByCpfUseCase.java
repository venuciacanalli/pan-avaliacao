package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.domain.entities.Client;

public interface IGetClientByCpfUseCase {
    Client run(String cpf) throws Exception;
}
