package br.com.project.backend.uni.modal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;


@Entity(name = "agenda")
@Table(name = "agenda")
@NoArgsConstructor
@Data
@CrossOrigin
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String event;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEvent;

    private String description;
    @NotEmpty
    private String type;

    public Agenda(String event, LocalDate date, String description, String type) {
        this.event = event;
        this.dateEvent = date;
        this.description = description;
        this.type = type;
    }
}
