package br.com.project.backend.uni.modal.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record RequestHomeContentDTO(Long id,
                                    @NotBlank
                                    String title,
                                    @NotBlank
                                    String content,
                                    String photoId,
                                    MultipartFile file
                                    ) {
}
