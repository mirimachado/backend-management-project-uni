package br.com.project.backend.uni.modal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@Entity(name = "visitor")
@Table(name = "visitor")
@NoArgsConstructor
@Data
@CrossOrigin
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String visitorName;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateVisit;

    public Visitor(String visitorName, LocalDate dateVisit) {
        this.visitorName = visitorName;
        this.dateVisit = dateVisit;
    }
}
