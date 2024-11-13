package br.com.project.backend.uni.modal.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record RequestVisitorInfoDTO(Long id, @NotBlank String aboutUs,
                                    @NotBlank String leadership,
                                    @NotBlank String socialMedia) {
}
