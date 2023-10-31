package br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos;

import java.util.Date;
import java.util.UUID;

public record ClientProductResponse(UUID id, UUID productId, String name, Date startDate) { }
