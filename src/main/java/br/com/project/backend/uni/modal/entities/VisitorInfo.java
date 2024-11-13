package br.com.project.backend.uni.modal.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity(name = "visitor_infos")
@Table(name = "visitor_infos")
@Data
@NoArgsConstructor
@CrossOrigin
public class VisitorInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String aboutUs;
    @NotEmpty
    private String leadership;
    @NotEmpty
    private String socialMedia;

    public VisitorInfo(String aboutUs, String leadership, String socialMedia) {
        this.aboutUs = aboutUs;
        this.leadership = leadership;
        this.socialMedia = socialMedia;
    }
}
