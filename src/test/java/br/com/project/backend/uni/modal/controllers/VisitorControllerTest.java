package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorDTO;
import br.com.project.backend.uni.modal.services.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class VisitorControllerTest {

    @Mock
    private VisitorService visitorService;

    @InjectMocks
    private VisitorController visitorController;

    @Test
    void getAllVisitors() {
        when(visitorService.getAllVisitors()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorController.getAllVisitors();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createVisitor() {
        RequestVisitorDTO data = new RequestVisitorDTO(1L,"nome_do_visitante", LocalDate.now());
        when(visitorService.createVisitor(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = visitorController.createVisitor(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}