package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import br.com.project.backend.uni.modal.entities.HomeContent;
import br.com.project.backend.uni.modal.repositories.HomeContentRepository;
import br.com.project.backend.uni.modal.services.utility.MongoDBServiceDelete;
import br.com.project.backend.uni.modal.services.utility.UploadService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeContentServiceTest {

    @Mock
    private HomeContentRepository repository;
    @Mock
    private UploadService uploadService;
    @Mock
    private MongoDBServiceDelete mongoDBServiceDelete;
    @InjectMocks
    private HomeContentService homeContentService;

    @Test
    void getHomeContent() {
        List<HomeContent> homeContentsObjs = repository.findAll();
        when(repository.findAll()).thenReturn(homeContentsObjs);
        ResponseEntity result = homeContentService.getHomeContent();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void createHomeContentCase1() {
        String generatedphotoId = "string";
        RequestHomeContentDTO data = new RequestHomeContentDTO(1L,"titulo", "conteudo", generatedphotoId, null);
        HomeContent homeContent = new HomeContent(data);
        when(uploadService.savePhoto(null)).thenReturn(generatedphotoId);
        when(repository.save(homeContent)).thenReturn(homeContent);
        ResponseEntity result = this.homeContentService.createHomeContent(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateHomeContentCase1() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestHomeContentDTO data = new RequestHomeContentDTO(id,"titulo", "conteudo", generatedphotoId, null);
        HomeContent homeContent = new HomeContent(data);
        when(repository.findById(id)).thenReturn(Optional.of(homeContent));
        when(uploadService.savePhoto(null)).thenReturn(generatedphotoId);
        when(repository.save(homeContent)).thenReturn(homeContent);
        ResponseEntity resultF = homeContentService.updateHomeContent(data, null);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void updateHomeContentCase2() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestHomeContentDTO data = new RequestHomeContentDTO(1L,"titulo", generatedphotoId,"descrição", null);
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = homeContentService.updateHomeContent(data, null);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteHomeContentCase1() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestHomeContentDTO data = new RequestHomeContentDTO(1L,"titulo", generatedphotoId,"descrição", null);
        HomeContent homeContent = new HomeContent(data);
        when(repository.findById(id)).thenReturn(Optional.of(homeContent));
        ResponseEntity resultF = homeContentService.deleteHomeContent(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    void deleteHomeContentCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = homeContentService.deleteHomeContent(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}