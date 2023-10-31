package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Table(name = "client_product")
@Entity(name = "ClientProductEntity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class ClientProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "clientId", foreignKey = @ForeignKey(name = "fk_client_product_client_id"))
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "productId", foreignKey = @ForeignKey(name = "fk_client_product_product_id"))
    private ProductEntity product;

    private Date startDate;
}
