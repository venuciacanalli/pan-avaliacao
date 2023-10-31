package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "address")
@Entity(name = "AddressEntity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    @Column(length = 10)
    private String number;
    @Column(length = 50)
    private String complement;
    private String neighborhood;
    @Column(length = 8)
    private String cep;
    private String city;
    @Column(length = 2)
    private String uf;
}