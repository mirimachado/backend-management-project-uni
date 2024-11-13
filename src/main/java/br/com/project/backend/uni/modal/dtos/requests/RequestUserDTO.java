package br.com.project.backend.uni.modal.dtos.requests;

import br.com.project.backend.uni.modal.emuns.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RequestUserDTO(
                             Long id,
                             @NotBlank
                             String name,
                             @NotBlank
                             String email,
                             @NotBlank
                             String password,
                             UserRole role,
                             @NotNull
                             @JsonFormat(pattern = "yyyy-MM-dd")
                             LocalDate birthday,
                             String username ,
                             @NotBlank
                             String login){

}
