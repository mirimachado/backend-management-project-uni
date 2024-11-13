package br.com.project.backend.uni.modal.dtos.requests;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RequestVisitorDTO(Long id,
                                @NotBlank
                                String visitorName,
                                @NotBlank
                                LocalDate dateVisit
                                ) {
}
