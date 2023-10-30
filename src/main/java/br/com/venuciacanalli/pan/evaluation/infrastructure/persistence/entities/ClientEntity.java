package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities;

import jakarta.persistence.*;
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
    @Column(length = 11)
    private String cpf;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_address_client"))
    private AddressEntity address;

    public ClientEntity(String cpf, String name){
        this.cpf = cpf;
        this.name = name;
    }

}
