package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorDTO;
import br.com.project.backend.uni.modal.entities.Visitor;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class VisitorRepositoryTest {

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Visitor Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestVisitorDTO data = new RequestVisitorDTO(id,"nome_do_visitante", LocalDate.now());
        this.createVisitor(data);
        Optional<Visitor> result = this.visitorRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Visitor Object from Data Base when Visitor no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<Visitor> result = this.visitorRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private Visitor createVisitor(RequestVisitorDTO data){
        Visitor newVisitor = new Visitor(data);
        this.entityManager.persist(newVisitor);
        return newVisitor;
    }
}