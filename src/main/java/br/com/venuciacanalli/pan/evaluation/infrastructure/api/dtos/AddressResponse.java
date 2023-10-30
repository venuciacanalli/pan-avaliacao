package br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos;

public record AddressResponse (Long id, String street, String number, String complement, String neighborhood, String cep,
                               String city, String uf) {
}