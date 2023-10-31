package br.com.venuciacanalli.pan.evaluation.application.usecases;

import br.com.venuciacanalli.pan.evaluation.domain.entities.ClientProduct;

import java.util.List;

public interface IGetProductsByClientUseCase {
    List<ClientProduct> run(String cpf);
}
