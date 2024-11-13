package br.com.project.backend.uni.modal.entities;

import br.com.project.backend.uni.modal.emuns.GroupType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;


@Entity(name = "groups")
@Table(name = "groups")
@NoArgsConstructor
@Data
@CrossOrigin
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String participant;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdOn;

    private String messages;
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private GroupType type;

    public Group(String participant, LocalDate createdOn, String messages, GroupType type) {
        this.participant = participant;
        this.createdOn = createdOn;
        this.messages = messages;
        this.type = type;
    }
}
