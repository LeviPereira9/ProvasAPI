package edu.criandoapi.criandoapi.domain.user;

public record RegisterDto(String login, String password, UserRole role) {

    public RegisterDto(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

}
