package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "client")
@Entity(name = "ClientEntity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "cpf")
public class ClientEntity {

    @Id
    private String cpf;

    private String name;

}
