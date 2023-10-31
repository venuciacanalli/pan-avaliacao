package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;

public interface IGetAddressByCepUseCase {
    CEPAddress run(String cep);
}
