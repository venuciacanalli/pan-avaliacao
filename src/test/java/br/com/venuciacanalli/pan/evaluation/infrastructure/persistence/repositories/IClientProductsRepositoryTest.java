package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientProductEntity;
import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IClientProductsRepositoryTest {

    @Autowired
    private IClientProductsRepository clientProductsRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IClientRepository clientRepository;

    @Test
    @DisplayName("should return client products from client cpf ")
    void shouldReturnClientProductsFromClientCpf() {

        ClientEntity clientEntity = this.clientRepository.save(new ClientEntity("86109026093", "John Smith"));
        ProductEntity productEntity= this.productRepository.save(new ProductEntity(null, "Financiamento de Veículo"));
        ClientProductEntity clientProductEntity = new ClientProductEntity(null, clientEntity, productEntity, new Date());
        this.clientProductsRepository.save(clientProductEntity);

        List<ClientProductEntity> clientProducts = this.clientProductsRepository.findByClientCpf("86109026093");
        assertNotNull(clientProducts);
        assertEquals(1, clientProducts.size());

        ClientProductEntity clientProductSaved = clientProducts.get(0);
        assertNotNull(clientProductSaved.getId());
        assertNotNull(clientProductSaved.getClient());
        assertEquals("86109026093", clientProductSaved.getClient().getCpf());
        assertEquals("John Smith", clientProductSaved.getClient().getName());
        assertNotNull(clientProductSaved.getProduct());
        assertNotNull(clientProductSaved.getProduct().getId());
        assertEquals("Financiamento de Veículo", clientProductSaved.getProduct().getName());
    }

    @Test
    @DisplayName("should return empty list when not exists client ")
    void shouldReturnEmptyListWhenNotExistClient() {
        List<ClientProductEntity> clientProducts = this.clientProductsRepository.findByClientCpf("86109026093");
        assertNotNull(clientProducts);
        assertTrue(clientProducts.isEmpty());
    }

    @Test
    @DisplayName("should return empty list when not exists products for client ")
    void shouldReturnEmptyListWhenNotExistProductsForClient() {
        this.clientRepository.save(new ClientEntity("86109026093", "John Smith"));
        List<ClientProductEntity> clientProducts = this.clientProductsRepository.findByClientCpf("86109026093");
        assertNotNull(clientProducts);
        assertTrue(clientProducts.isEmpty());
    }

}