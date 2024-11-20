package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestAgendaDTO;
import br.com.project.backend.uni.modal.entities.Agenda;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
class AgendaRepositoryTest {

    @Autowired
    AgendaRepository agendaRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Agenda Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestAgendaDTO data = new RequestAgendaDTO(id, "Event test", LocalDate.now(), "Description test","Test type");
        this.createAgenda(data);
        Optional<Agenda> result = this.agendaRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Agenda Object from Data Base when Agenda no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<Agenda> result = this.agendaRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private Agenda createAgenda(RequestAgendaDTO data){
       Agenda newAgenda = new Agenda(data);
       this.entityManager.persist(newAgenda);
       return newAgenda;
    }
}