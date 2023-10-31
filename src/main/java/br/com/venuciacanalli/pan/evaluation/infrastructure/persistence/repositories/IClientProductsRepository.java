package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IClientProductsRepository extends JpaRepository<ClientProductEntity, UUID> {

    List<ClientProductEntity> findByClientCpf(String cpf);
}
