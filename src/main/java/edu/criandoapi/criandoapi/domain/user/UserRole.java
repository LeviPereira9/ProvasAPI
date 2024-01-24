package edu.criandoapi.criandoapi.domain.user;

public enum UserRole {
    ADMIN("admin"),
    EDITOR("editor"),
    VIEWER("viewer");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
