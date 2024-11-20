package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestVisitorInfoDTO;
import br.com.project.backend.uni.modal.entities.VisitorInfo;
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
class VisitorInfoRepositoryTest {

    @Autowired
    VisitorInfoRepository visitorInfoRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Visitor Info Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestVisitorInfoDTO data = new RequestVisitorInfoDTO(id, "Event test", "midia social","Test type");
        this.createVisitorInfo(data);
        Optional<VisitorInfo> result = this.visitorInfoRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Visitor Info Object from Data Base when Visitor Info no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<VisitorInfo> result = this.visitorInfoRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private VisitorInfo createVisitorInfo(RequestVisitorInfoDTO data){
        VisitorInfo newVisitorInfo = new VisitorInfo(data);
        this.entityManager.persist(newVisitorInfo);
        return newVisitorInfo;
    }
}