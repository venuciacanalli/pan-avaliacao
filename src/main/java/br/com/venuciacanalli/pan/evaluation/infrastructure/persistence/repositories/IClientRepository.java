package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByCpf(@Param("cpf") @NotNull String cpf);
}
