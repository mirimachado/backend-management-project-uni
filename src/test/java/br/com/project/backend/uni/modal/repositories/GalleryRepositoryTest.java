package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.emuns.GalleryType;
import br.com.project.backend.uni.modal.entities.Gallery;
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
class GalleryRepositoryTest {

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Gallery Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestGalleryDTO data = new RequestGalleryDTO(1L,"titulo",  LocalDate.now(),"id_da_foto","descrição", GalleryType.UNI_TODOS, null);
        this.createGallery(data);
        Optional<Gallery> result = this.galleryRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Gallery Object from Data Base when Gallery no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<Gallery> result = this.galleryRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private Gallery createGallery(RequestGalleryDTO data){
        Gallery newGallery = new Gallery(data);
        this.entityManager.persist(newGallery);
        return newGallery;
    }
}