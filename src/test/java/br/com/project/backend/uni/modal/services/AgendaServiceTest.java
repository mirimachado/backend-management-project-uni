package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestAgendaDTO;
import br.com.project.backend.uni.modal.entities.Agenda;
import br.com.project.backend.uni.modal.repositories.AgendaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @Mock
    private AgendaRepository repository;

    @InjectMocks
    private AgendaService agendaService;

    @Test
    void getAllAgenda() {
        List<Agenda> agendasObj = repository.findAll();
        when(repository.findAll()).thenReturn(agendasObj);
        ResponseEntity result = agendaService.getAllAgenda();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve criar a agenda obj se os dados passados estiverem tudo ok")
    void createAgendaCase1() {
        RequestAgendaDTO agenda = new RequestAgendaDTO(1L, "Test event", LocalDate.now(), "Description test","Test type");
        Agenda agenda1 = new Agenda(agenda);
        when(repository.save(agenda1)).thenReturn(agenda1);
        ResponseEntity result = this.agendaService.createAgenda(agenda);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    void updateAgendaCase1() {
        Long id = 1L;
        RequestAgendaDTO data = new RequestAgendaDTO(id, "Test event", LocalDate.now(), "Description test","Test type");
        Agenda agenda = new Agenda(data);
        when(repository.findById(id)).thenReturn(Optional.of(agenda));
        when(repository.save(agenda)).thenReturn(agenda);
        ResponseEntity resultF = agendaService.updateAgenda(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateAgendaCase2() {
        Long id = 1L;
        RequestAgendaDTO data = new RequestAgendaDTO(id, "Test event", LocalDate.now(), "Description test","Test type");
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = agendaService.updateAgenda(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteAgendaCase1() {
        Long id = 1L;
        RequestAgendaDTO data = new RequestAgendaDTO(id, "Test event", LocalDate.now(), "Description test","Test type");
        Agenda agenda = new Agenda(data);
        when(repository.findById(id)).thenReturn(Optional.of(agenda));
        ResponseEntity resultF = agendaService.deleteAgenda(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteAgendaCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = agendaService.deleteAgenda(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}