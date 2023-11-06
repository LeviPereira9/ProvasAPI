package edu.criandoapi.criandoapi.controller.dto;

import edu.criandoapi.criandoapi.domain.model.Opcao;

public record OpcaoDto(String letra, String texto) {

    public OpcaoDto(Opcao model) {
        this(model.getLetra(), model.getTexto());
    }

    public Opcao toModel() {
        Opcao model = new Opcao();

        model.setLetra(this.letra);
        model.setTexto(this.texto);

        return model;
    }
}
