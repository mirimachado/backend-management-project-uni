package br.com.project.backend.uni.modal.emuns;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UserRole {
    @JsonProperty("ADMIN")
    ADMIN("ADMIN"),
    @JsonProperty("USER")
    USER("USER");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
