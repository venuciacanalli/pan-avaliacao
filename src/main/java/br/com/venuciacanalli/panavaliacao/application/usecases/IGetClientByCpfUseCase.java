package br.com.venuciacanalli.panavaliacao.application.usecases;

import br.com.venuciacanalli.panavaliacao.domain.entities.Client;

public interface IGetClientByCpfUseCase {
    Client run(String cpf);
}
