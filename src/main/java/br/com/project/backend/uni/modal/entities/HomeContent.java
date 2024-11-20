package br.com.project.backend.uni.modal.entities;


import br.com.project.backend.uni.modal.dtos.requests.RequestHomeContentDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Entity(name = "home_content")
@Table(name = "home_content")
@NoArgsConstructor
@Data
@CrossOrigin
public class HomeContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String photoId;
    @NotNull
    private String content;

    public HomeContent(String title, String photoId, String content) {
        this.title = title;
        this.photoId = photoId;
        this.content = content;
    }

    public HomeContent(RequestHomeContentDTO data){
        this.title = data.title();
        this.photoId = data.photoId();
        this.content = data.content();
    }

}
