package br.com.venuciacanalli.pan.evaluation.domain.entities;

public record CEPAddress(String street, String numberInterval, String neighborhood, String cep,
                         String city, String uf) {
}
