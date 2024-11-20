package br.com.project.backend.uni.modal.services;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.dtos.requests.RequestUserPassword;
import br.com.project.backend.uni.modal.emuns.UserRole;
import br.com.project.backend.uni.modal.entities.User;
import br.com.project.backend.uni.modal.repositories.UserRepository;
import br.com.project.backend.uni.modal.services.utility.EmailService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    @Transactional
    public ResponseEntity createUser(RequestUserDTO data){
        Optional<User> optionalUserName = repository.findByUsername(data.username());
        Optional<User> optionalUserEmail = repository.findByEmail(data.email());
        Optional<User> optionalUserLogin = repository.findByLogin(data.login());

        if (optionalUserName.isPresent()){
            return ResponseEntity.badRequest().body("User with this username already exists.");
        } else if (optionalUserEmail.isPresent()) {
            return ResponseEntity.badRequest().body("User with this e-mail already exists.");
        } else if (optionalUserLogin.isPresent()) {
            return ResponseEntity.badRequest().body("User with this login already exists.");
        } else {
            if (!data.email().endsWith("@gmail.com")) {
                return ResponseEntity.badRequest().body("The email must end with @gmail.com");
            }
            User user = new User();
            user.setName(data.name());
            user.setUsername(data.username());
            user.setEmail(data.email());
            user.setBirthday(data.birthday());
            user.setPassword(passwordEncoder.encode(data.password()));
            user.setLogin(data.login());
            user.setRole(data.role());
            repository.save(user);
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity getAllUsers() {
        List<User> userList = repository.findAll();
        return ResponseEntity.ok(userList);
    }

    @Transactional
    public ResponseEntity updateUser(RequestUserDTO data) {
        Optional<User> optionalUser = repository.findById(data.id());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(data.name());
            user.setUsername(data.username());
            user.setEmail(data.email());
            user.setPassword(passwordEncoder.encode(data.password()));
            user.setBirthday(data.birthday());
            user.setRole(UserRole.USER);
            repository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Transactional
    public ResponseEntity updatePassword(RequestUserPassword data) throws MessagingException, UnsupportedEncodingException {
        Optional<User> optionalUser = repository.findByEmail(data.email());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            String email = user.getEmail();
            emailService.sendMailWithInline(email);
            return ResponseEntity.ok("Confirmação enviada para o e-mail do destinatário. ");
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id){
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            repository.delete(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
