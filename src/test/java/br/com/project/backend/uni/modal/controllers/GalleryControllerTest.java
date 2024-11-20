package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.emuns.GalleryType;
import br.com.project.backend.uni.modal.services.GalleryService;
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
class GalleryControllerTest {

    @Mock
    private GalleryService galleryService;

    @InjectMocks
    private GalleryController galleryController;

    @Test
    void getAllGallery() {
        when(galleryService.getAllGallery()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = galleryController.getAllGallery();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createGallery() {
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo",  LocalDate.now(),"id_da_foto","descrição", GalleryType.UNI_TODOS, null);
        when(galleryService.createGallery(data, null)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = galleryController.createGallery(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateGallery() {
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo",  LocalDate.now(),"id_da_foto","descrição", GalleryType.UNI_TODOS, null);
        when(galleryService.updateGallery(data, null)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = galleryController.updateGallery(data, null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteGallery() {
        Long id = 1L;
        when(galleryService.deleteGallery(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = galleryController.deleteGallery(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}