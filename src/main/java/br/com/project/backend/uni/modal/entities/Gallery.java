package br.com.project.backend.uni.modal.entities;

import br.com.project.backend.uni.modal.dtos.requests.RequestGalleryDTO;
import br.com.project.backend.uni.modal.emuns.GalleryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@Entity(name = "gallery")
@Table(name = "gallery")
@NoArgsConstructor
@Data
@CrossOrigin
public class Gallery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePhoto;
    @NotNull
    private String photoId;
    @NotEmpty
    private String title;

    private String description;
//    @NotEmpty
    @Enumerated(EnumType.STRING)
    private GalleryType type;

    public Gallery(String description, String title, String photoId, LocalDate date, GalleryType type) {
        this.description = description;
        this.title = title;
        this.photoId = photoId;
        this.datePhoto = date;
        this.type = type;
    }

    public Gallery(RequestGalleryDTO data){
        this.description = data.description();
        this.title = data.title();
        this.photoId = data.photoId();
        this.datePhoto = data.date();
        this.type = data.type();
    }
}
