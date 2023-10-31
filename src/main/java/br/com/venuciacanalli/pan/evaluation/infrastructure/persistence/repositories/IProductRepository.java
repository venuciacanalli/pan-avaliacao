package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<ProductEntity, UUID> {
}
