package edu.criandoapi.criandoapi.controller.dto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.criandoapi.criandoapi.domain.model.Respostas;

public record RespostasDto(boolean temImagem, List<OpcaoDto> opcoesDto) {

    public RespostasDto(Respostas model) {
        this(
                model.isTemImagem(),
                Optional
                        .ofNullable(model.getOpcoes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(OpcaoDto::new)
                        .collect(Collectors.toList()));
    }

    public Respostas toModel() {
        Respostas model = new Respostas();

        model.setTemImagem(this.temImagem);
        model.setOpcoes(
                Optional
                        .ofNullable(this.opcoesDto)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(OpcaoDto::toModel)
                        .collect(Collectors.toList()));

        return model;
    }
}
