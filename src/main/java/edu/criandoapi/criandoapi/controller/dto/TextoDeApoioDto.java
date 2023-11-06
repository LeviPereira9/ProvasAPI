package edu.criandoapi.criandoapi.controller.dto;

import edu.criandoapi.criandoapi.domain.model.TextoDeApoio;

public record TextoDeApoioDto(int numero, boolean temImagem, String texto, String fonte) {

    public TextoDeApoioDto(TextoDeApoio model) {
        this(model.getNumero(), model.isTemImagem(), model.getTexto(), model.getFonte());
    }

    public TextoDeApoio toModel() {
        TextoDeApoio model = new TextoDeApoio();

        model.setNumero(this.numero);
        model.setTemImagem(this.temImagem);
        model.setTexto(this.texto);
        model.setFonte(this.fonte);

        return model;
    }

}
