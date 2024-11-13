package br.com.project.backend.uni.modal.dtos.requests;

import br.com.project.backend.uni.modal.emuns.GroupType;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RequestGroupDTO(Long id,
                              @NotBlank String participant,
                              @NotBlank LocalDate createdOn,
                              String messages,
                              @NotBlank GroupType type){
}
