package edu.criandoapi.criandoapi.controller.dto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.criandoapi.criandoapi.domain.model.EnunciadoAssercoes;

public record EnunciadoAssercoesDto(
        boolean temRelacao,
        String textoRelacao,
        String posAssercoes,
        List<AssercaoDto> assercoes) {

    public EnunciadoAssercoesDto(EnunciadoAssercoes model) {
        this(
                model.isTemRelacao(),
                model.getTextoRelacao(),
                model.getPosAssercoes(),
                Optional
                        .ofNullable(model.getAssercoes())
                        .orElse(Collections.emptyList())
                        .stream().map(AssercaoDto::new)
                        .collect(Collectors.toList()));
    }

    public EnunciadoAssercoes toModel() {
        EnunciadoAssercoes model = new EnunciadoAssercoes();

        model.setTemRelacao(this.temRelacao);
        model.setTextoRelacao(this.textoRelacao);
        model.setPosAssercoes(this.posAssercoes);
        model.setAssercoes(
                Optional
                        .ofNullable(this.assercoes)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(AssercaoDto::toModel)
                        .collect(Collectors.toList()));

        return model;
    }

}
