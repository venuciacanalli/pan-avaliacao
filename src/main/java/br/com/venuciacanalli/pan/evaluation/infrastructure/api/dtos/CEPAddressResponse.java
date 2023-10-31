package br.com.venuciacanalli.pan.evaluation.infrastructure.api.dtos;

public record CEPAddressResponse (String street, String numberInterval, String neighborhood, String cep,
                                  String city, String uf) {
}
