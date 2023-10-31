package br.com.venuciacanalli.pan.evaluation.domain.entities;

import java.util.UUID;

public record Address(UUID id, String street, String number, String complement, String neighborhood, String cep,
                      String city, String uf) {
}