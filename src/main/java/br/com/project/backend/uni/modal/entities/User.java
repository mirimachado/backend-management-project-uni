package br.com.project.backend.uni.modal.entities;

import br.com.project.backend.uni.modal.dtos.requests.RequestUserDTO;
import br.com.project.backend.uni.modal.dtos.requests.RequestUserPassword;
import br.com.project.backend.uni.modal.emuns.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;


@Entity(name = "users")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@CrossOrigin
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Column(unique = true)
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Column(unique = true)
    private String email;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @NotEmpty
    @Column(unique = true)
    private String login;

    public User(String password, UserRole userRole, String login, String username){
        this.password = password;
        this.role = userRole;
        this.login = login;
        this.username = username;
    }

    public User(RequestUserDTO requestUserDTO) {
        this.name = requestUserDTO.name();
        this.password = requestUserDTO.password();
        this.email = requestUserDTO.email();
        this.birthday = requestUserDTO.birthday();
        this.role = requestUserDTO.role();
        this.login = requestUserDTO.login();
        this.username = requestUserDTO.username();
    }

    public User(RequestUserPassword data) {
        this.email = data.email();
    }
}
