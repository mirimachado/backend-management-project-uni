package br.com.project.backend.uni.modal.repositories;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.emuns.UserRole;
import br.com.project.backend.uni.modal.entities.User;
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
class UserRepositoryTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get User Object successfully from Data Base")
    void findByIdCase1() {
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", "Description test","Test type", UserRole.USER, LocalDate.now(),"nome", "login");
        this.createUser(data);
        Optional<User> result = this.userRepository.findById(id);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get User Object from Data Base when User no exists")
    void findByIdCase2() {
        Long id = 1L;
        Optional<User> result = this.userRepository.findById(id);
        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(RequestUserDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }

    @Test
    @DisplayName("Should get User Object successfully from Data Base")
    void findByUsernameCase1() {
        String name = "name test";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", "Description test","Test type", UserRole.USER, LocalDate.now(), name, "login");
        this.createUser(data);
        Optional<User> result = this.userRepository.findByUsername(name);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get User Object from Data Base when User no exists")
    void findByUsernameCase2() {
        String name = "name test";
        Optional<User> result = this.userRepository.findByUsername(name);
        assertThat(result.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Should get User Object successfully from Data Base")
    void findByEmailCase1() {
        String email = "email test";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", email,"Test type", UserRole.USER, LocalDate.now(),"username", "login");
        this.createUser(data);
        Optional<User> result = this.userRepository.findByEmail(email);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get User Object from Data Base when User no exists")
    void findByEmailCase2() {
        String email = "email test";
        Optional<User> result = this.userRepository.findByEmail(email);
        assertThat(result.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should get User Object successfully from Data Base")
    void findByLoginCase1() {
        String login = "login test";
        Long id = 1L;
        RequestUserDTO data = new RequestUserDTO(id, "Event test", "Description test","Test type", UserRole.USER, LocalDate.now(),"teste" , login);
        this.createUser(data);
        Optional<User> result = this.userRepository.findByLogin(login);
        assertThat(result.isPresent()).isTrue();

    }

    @Test
    @DisplayName("Should not get User Object from Data Base when User no exists")
    void findByLoginCase2() {
        String login = "login test";
        Optional<User> result = this.userRepository.findByLogin(login);
        assertThat(result.isEmpty()).isTrue();
    }
}