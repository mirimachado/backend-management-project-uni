package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestGroupDTO;
import br.com.project.backend.uni.modal.emuns.GroupType;
import br.com.project.backend.uni.modal.entities.Group;
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
class GroupRepositoryTest {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Group Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(id, "participantes", LocalDate.now(),"mensagens", GroupType.BANDA);
        this.createGroup(data);
        Optional<Group> result = this.groupRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get Group Object from Data Base when Group no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<Group> result = this.groupRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private Group createGroup(RequestGroupDTO data){
        Group newGroup = new Group(data);
        this.entityManager.persist(newGroup);
        return newGroup;
    }
}