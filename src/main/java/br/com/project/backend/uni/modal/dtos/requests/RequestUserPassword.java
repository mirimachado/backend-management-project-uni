package br.com.project.backend.uni.modal.dtos.requests;


import jakarta.validation.constraints.NotBlank;

public record RequestUserPassword(@NotBlank
                                  String email) {

}
