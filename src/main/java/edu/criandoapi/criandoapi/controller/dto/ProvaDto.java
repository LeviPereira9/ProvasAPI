package edu.criandoapi.criandoapi.controller.dto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.criandoapi.criandoapi.domain.model.Prova;

public record ProvaDto(Long id, String nome, int anoDeAplicacao, String fonte, List<QuestaoDto> questoes) {

    public ProvaDto(Prova model) {
        this(
                model.getId(),
                model.getNome(),
                model.getAnoDeAplicacao(),
                model.getFonte(),
                Optional
                        .ofNullable(model.getQuestoes())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(QuestaoDto::new)
                        .collect(Collectors.toList()));
    }

    public Prova toModel() {
        Prova model = new Prova();

        model.setId(this.id);
        model.setNome(this.nome);
        model.setAnoDeAplicacao(this.anoDeAplicacao);
        model.setFonte(this.fonte);
        model.setQuestoes(
                Optional
                        .ofNullable(this.questoes)
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(QuestaoDto::toModel)
                        .collect(Collectors.toList()));

        return model;

    }

}
