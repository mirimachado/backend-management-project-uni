package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorDTO;
import br.com.project.backend.uni.modal.entities.Visitor;
import br.com.project.backend.uni.modal.repositories.VisitorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorServiceTest {

    @Mock
    private VisitorRepository repository;

    @InjectMocks
    private VisitorService visitorService;


    @Test
    void getAllVisitors() {
        List<Visitor> visitorsObj = repository.findAll();
        when(repository.findAll()).thenReturn(visitorsObj);
        ResponseEntity result = visitorService.getAllVisitors();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createVisitor() {
        RequestVisitorDTO data = new RequestVisitorDTO(1L,"nome_do_visitante", LocalDate.now());
        Visitor visitor = new Visitor(data);
        when(repository.save(visitor)).thenReturn(visitor);
        ResponseEntity result = this.visitorService.createVisitor(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

}