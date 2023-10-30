package br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.repositories;

import br.com.venuciacanalli.pan.evaluation.infrastructure.persistence.entities.ClientEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class IClientRepositoryTest {

    @Autowired
    private IClientRepository clientRepository;

    @Test
    @DisplayName("should return client with cpf informed when findByCPF")
    void shouldReturnClientWithCpfInformedWhenFindByCpf() {
        this.clientRepository.save(new ClientEntity("86109026093", "John Smith"));
        var optClient = this.clientRepository.findByCpf("86109026093");
        assertTrue(optClient.isPresent());
        var client = optClient.get();
        assertEquals("86109026093", client.getCpf());
        assertEquals("John Smith", client.getName());
    }

    @Test
    @DisplayName("should return empty value when there is no client for the given cpf")
    void shouldReturnEmptyValueWhenThereIsNoClientForTheGivenCpf() {
        var optClient = this.clientRepository.findByCpf("86109026093");
        assertTrue(optClient.isEmpty());
    }
}