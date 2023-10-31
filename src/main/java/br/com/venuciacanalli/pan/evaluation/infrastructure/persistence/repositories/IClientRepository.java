package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByCpf(@Param("cpf") String cpf);

    boolean existsByCpf(String cpf);
}
