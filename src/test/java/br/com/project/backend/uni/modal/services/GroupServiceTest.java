package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestGroupDTO;
import br.com.project.backend.uni.modal.emuns.GroupType;
import br.com.project.backend.uni.modal.entities.Group;
import br.com.project.backend.uni.modal.repositories.GroupRepository;
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
class GroupServiceTest {

    @Mock
    private GroupRepository repository;

    @InjectMocks
    private GroupService groupService;

    @Test
    void getAllGroup() {
        List<Group> agendasObj = repository.findAll();
        when(repository.findAll()).thenReturn(agendasObj);
        ResponseEntity result = groupService.getAllGroup();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createGroup() {
        RequestGroupDTO data = new RequestGroupDTO(1L, "Test event", LocalDate.now(), "Description test", GroupType.MIDIA);
        Group group = new Group(data);
        when(repository.save(group)).thenReturn(group);
        ResponseEntity result = this.groupService.createGroup(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateGroupCase1() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(1L, "Test event", LocalDate.now(), "Description test", GroupType.MIDIA);
        Group group = new Group(data);
        when(repository.findById(id)).thenReturn(Optional.of(group));
        when(repository.save(group)).thenReturn(group);
        ResponseEntity resultF = groupService.updateGroup(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateGroupCase2() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(1L, "Test event", LocalDate.now(), "Description test", GroupType.MIDIA);
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = groupService.updateGroup(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteGroupCase1() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(1L, "Test event", LocalDate.now(), "Description test", GroupType.MIDIA);
        Group group = new Group(data);
        when(repository.findById(id)).thenReturn(Optional.of(group));
        ResponseEntity resultF = groupService.deleteGroup(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteGroupCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = groupService.deleteGroup(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}