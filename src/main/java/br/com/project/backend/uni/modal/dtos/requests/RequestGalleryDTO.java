package br.com.project.backend.uni.modal.dtos.requests;

import br.com.project.backend.uni.modal.emuns.GalleryType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record RequestGalleryDTO(Long id,
                                @NotBlank
                                String title,
                                @NotNull
                                @JsonFormat(pattern = "yyyy-MM-dd")
                                LocalDate date,
                                String photoId,
                                String description,
                                GalleryType type,
                                MultipartFile multipart
                                ) {
}
