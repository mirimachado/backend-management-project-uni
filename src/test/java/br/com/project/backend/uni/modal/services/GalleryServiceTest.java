package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.emuns.GalleryType;
import br.com.project.backend.uni.modal.entities.Gallery;
import br.com.project.backend.uni.modal.repositories.GalleryRepository;
import br.com.project.backend.uni.modal.services.utility.MongoDBServiceDelete;
import br.com.project.backend.uni.modal.services.utility.UploadService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GalleryServiceTest {

    @Mock
    private GalleryRepository repository;

    @Mock
    private UploadService uploadService;

    @Mock
    private MongoDBServiceDelete mongoDBServiceDelete;

    @InjectMocks
    private GalleryService galleryService;

    @Test
    void getAllGallery() {
        List<Gallery> galleryObjs = repository.findAll();
        when(repository.findAll()).thenReturn(galleryObjs);
        ResponseEntity result = galleryService.getAllGallery();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createGalleryCase1() {
        String generatedphotoId = "string";
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo", LocalDate.now(), generatedphotoId,"descrição", GalleryType.UNI_TODOS, null);
        Gallery gallery = new Gallery(data);
        when(uploadService.savePhoto(null)).thenReturn(generatedphotoId);
        when(repository.save(gallery)).thenReturn(gallery);
        ResponseEntity result = this.galleryService.createGallery(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateGalleryCase1() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo", LocalDate.now(), generatedphotoId,"descrição", GalleryType.UNI_TODOS, null);
        Gallery gallery = new Gallery(data);
        when(repository.findById(id)).thenReturn(Optional.of(gallery));
        when(uploadService.savePhoto(null)).thenReturn(generatedphotoId);
        when(repository.save(gallery)).thenReturn(gallery);
        ResponseEntity resultF = galleryService.updateGallery(data, null);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void updateGalleryCase2() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo", LocalDate.now(), generatedphotoId,"descrição", GalleryType.UNI_TODOS, null);
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = galleryService.updateGallery(data, null);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteGalleryCase1() {
        Long id = 1L;
        String generatedphotoId = "string";
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo", LocalDate.now(), generatedphotoId,"descrição", GalleryType.UNI_TODOS, null);
        Gallery gallery = new Gallery(data);
        when(repository.findById(id)).thenReturn(Optional.of(gallery));
        ResponseEntity resultF = galleryService.deleteGallery(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
    @Test
    void deleteGalleryCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = galleryService.deleteGallery(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}