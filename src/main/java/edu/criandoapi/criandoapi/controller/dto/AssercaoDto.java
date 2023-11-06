package edu.criandoapi.criandoapi.controller.dto;

import edu.criandoapi.criandoapi.domain.model.Assercao;

public record AssercaoDto(String numeroRomano, String texto) {

    public AssercaoDto(Assercao model) {
        this(model.getNumeroRomano(), model.getTexto());
    }

    public Assercao toModel() {
        Assercao model = new Assercao();

        model.setNumeroRomano(this.numeroRomano);
        model.setTexto(this.texto);

        return model;
    }

}
