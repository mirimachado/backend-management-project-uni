package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.dtos.requests.RequestUserPassword;
import br.com.project.backend.uni.modal.emuns.UserRole;
import br.com.project.backend.uni.modal.entities.User;
import br.com.project.backend.uni.modal.repositories.UserRepository;
import br.com.project.backend.uni.modal.services.utility.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Test
    void getAllUsers() {
        List<User> usersObj = repository.findAll();
        when(repository.findAll()).thenReturn(usersObj);
        ResponseEntity result = userService.getAllUsers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createUserCase1() {
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";

        RequestUserDTO data = new RequestUserDTO(1L, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.findByLogin(login)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(repository.save(user)).thenReturn(user);
        ResponseEntity result = this.userService.createUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createUserCase2(){
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        RequestUserDTO data = new RequestUserDTO(1L, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findByUsername(username)).thenReturn(Optional.of(user));
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.findByLogin(login)).thenReturn(Optional.empty());
        ResponseEntity result = this.userService.createUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    void createUserCase3(){
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        RequestUserDTO data = new RequestUserDTO(1L, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(repository.findByEmail(email)).thenReturn(Optional.of(user));
        when(repository.findByLogin(login)).thenReturn(Optional.empty());
        ResponseEntity result = this.userService.createUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }
    @Test
    void createUserCase4(){
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        RequestUserDTO data = new RequestUserDTO(1L, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        when(repository.findByLogin(login)).thenReturn(Optional.of(user));
        ResponseEntity result = this.userService.createUser(data);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

    }


    @Test
    void updateUserCase1() {
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(repository.save(user)).thenReturn(user);
        ResponseEntity resultF = userService.updateUser(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateUserCase2() {
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = userService.updateUser(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void updatePasswordCase1() throws MessagingException, UnsupportedEncodingException {
        String email = "teste@gmail.com";
        RequestUserPassword data = new RequestUserPassword(email);
        User user = new User(data);
        when(repository.findByEmail(email)).thenReturn(Optional.of(user));
        ResponseEntity resultF = userService.updatePassword(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updatePasswordCase2() throws MessagingException, UnsupportedEncodingException {
        String email = "teste@gmail.com";
        RequestUserPassword data = new RequestUserPassword(email);
        User user = new User(data);
        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        ResponseEntity resultF = userService.updatePassword(data);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteUserCase1() {
        String username = "username";
        String email = "teste@gmail.com";
        String login = "teste_login";
        String password = "password_encoder";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", email, password, UserRole.USER, LocalDate.now(), username, login);
        User user = new User(data);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        ResponseEntity resultF = userService.deleteUser(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void deleteUserCase2() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        ResponseEntity resultF = userService.deleteUser(id);
        assertThat(resultF.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}