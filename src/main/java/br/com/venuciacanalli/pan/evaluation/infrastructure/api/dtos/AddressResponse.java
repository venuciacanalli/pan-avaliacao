package br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos;

import java.util.UUID;

public record AddressResponse (UUID id, String street, String number, String complement, String neighborhood, String cep,
                               String city, String uf) {
}