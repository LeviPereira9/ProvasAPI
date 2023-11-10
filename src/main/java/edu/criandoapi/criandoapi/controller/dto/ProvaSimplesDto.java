package edu.criandoapi.criandoapi.controller.dto;

public record ProvaSimplesDto(Long id, String nome) {
    public ProvaSimplesDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
