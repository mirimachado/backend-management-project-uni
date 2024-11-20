package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import br.com.project.backend.uni.modal.entities.HomeContent;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class HomeContentRepositoryTest {

    @Autowired
    HomeContentRepository homeContentRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Home Content Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestHomeContentDTO data = new RequestHomeContentDTO(id,"titulo", "conteudo", "id_da_foto", null);
        this.createHomeContent(data);
        Optional<HomeContent> result = this.homeContentRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Home Content Object from Data Base when Home Content no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<HomeContent> result = this.homeContentRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private HomeContent createHomeContent(RequestHomeContentDTO data){
        HomeContent newHomeContent = new HomeContent(data);
        this.entityManager.persist(newHomeContent);
        return newHomeContent;
    }
}