package br.com.project.backend.uni.modal.controllers;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.emuns.UserRole;
import br.com.project.backend.uni.modal.services.UserService;
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
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void getAllUsers() {
        when(userService.getAllUsers()).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = userController.getAllUsers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createUser() {
        RequestUserDTO data = new RequestUserDTO(1L, "Event test", "Description test","Test type", UserRole.USER, LocalDate.now(), "name test", "login");
        when(userService.createUser(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = userController.createUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updatePassword() {
        RequestUserDTO data = new RequestUserDTO(1L, "Event test", "Description test","Test type", UserRole.USER, LocalDate.now(), "name test", "login");
        when(userService.updateUser(data)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = userController.updateUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteUser() {
        Long id = 1L;
        when(userService.deleteUser(id)).thenReturn(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
        ResponseEntity result = userController.deleteUser(id);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}