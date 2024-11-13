package br.com.project.backend.uni.modal.dtos.requests;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RequestAgendaDTO(Long id,
                               @NotBlank String event,
                               LocalDate date,
                               String description,
                               @NotBlank String type){
}
