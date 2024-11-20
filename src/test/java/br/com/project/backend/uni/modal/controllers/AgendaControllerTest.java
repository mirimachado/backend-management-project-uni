package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestAgendaDTO;
import br.com.project.backend.uni.modal.services.AgendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AgendaControllerTest {

    @Mock
    private AgendaService agendaService;

    @InjectMocks
    private AgendaController agendaController;

    @Test
    void getAllAgenda() throws Exception {
        when(agendaService.getAllAgenda()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = agendaController.getAllAgenda();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createAgenda() {
        RequestAgendaDTO data = new RequestAgendaDTO(1L, "Test event", LocalDate.now(), "Description test","Test type");
        when(agendaService.createAgenda(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = agendaController.createAgenda(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateAgenda() {
        Long id = 1L;
        RequestAgendaDTO data = new RequestAgendaDTO(id, "Test event", LocalDate.now(), "Description test","Test type");
        when(agendaService.updateAgenda(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = agendaController.updateAgenda(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteAgenda() {
        Long id = 1L;
        when(agendaService.deleteAgenda(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = agendaController.deleteAgenda(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}