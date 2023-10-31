package br.com.venuciacanalli.pan.evaluation.domain.entities;

public record Address(Long id, String street, String number, String complement, String neighborhood, String cep,
                      String city, String uf) {
}