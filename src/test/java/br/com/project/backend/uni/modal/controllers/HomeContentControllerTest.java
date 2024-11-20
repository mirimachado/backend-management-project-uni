package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import br.com.project.backend.uni.modal.services.HomeContentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeContentControllerTest {

    @Mock
    private HomeContentService homeContentService;

    @InjectMocks
    private HomeContentController homeContentController;

    @Test
    void getAllUsers() {
        when(homeContentService.getHomeContent()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = homeContentController.getAllUsers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateHomeContent() {
        Long id = 1L;
        RequestHomeContentDTO data = new RequestHomeContentDTO(id,"titulo", "conteudo", "id_da_foto", null);
        when(homeContentService.updateHomeContent(data, null)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = homeContentController.updateHomeContent(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createHomeContent() {
        Long id = 1L;
        RequestHomeContentDTO data = new RequestHomeContentDTO(id,"titulo", "conteudo", "id_da_foto", null);
        when(homeContentService.createHomeContent(data, null)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = homeContentController.createHomeContent(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteHomeContent() {
        Long id = 1L;
        when(homeContentService.deleteHomeContent(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = homeContentController.deleteHomeContent(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}