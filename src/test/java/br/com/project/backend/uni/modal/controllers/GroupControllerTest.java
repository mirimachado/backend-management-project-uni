package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestGroupDTO;
import br.com.project.backend.uni.modal.emuns.GroupType;
import br.com.project.backend.uni.modal.services.GroupService;
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
class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    @Test
    void getAllGroup() {
        when(groupService.getAllGroup()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = groupController.getAllGroup();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createGroup() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(id, "participantes", LocalDate.now(),"mensagens", GroupType.BANDA);
        when(groupService.createGroup(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = groupController.createGroup(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateGroup() {
        Long id = 1L;
        RequestGroupDTO data = new RequestGroupDTO(id, "participantes", LocalDate.now(),"mensagens", GroupType.BANDA);
        when(groupService.updateGroup(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = groupController.updateGroup(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteGroup() {
        Long id = 1L;
        when(groupService.deleteGroup(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = groupController.deleteGroup(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}