package br.com.venuciacanalli.pan.evaluation.domain.entities;

import java.util.Date;
import java.util.UUID;

public record ClientProduct(UUID id, UUID productId, String name, Date startDate) {
}
