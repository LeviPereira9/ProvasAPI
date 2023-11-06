package edu.criandoapi.criandoapi.controller.dto;

import edu.criandoapi.criandoapi.domain.model.Questao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record QuestaoDto(
                int numeroDaQuestao,
                boolean anulada,
                boolean temAssercoes,
                String enunciado,
                String opcaoCorreta,
                List<TextoDeApoioDto> textosDeApoioDto,
                RespostasDto respostasDto,
                EnunciadoAssercoesDto enunciadoAssercoesDto) {

        public QuestaoDto(Questao model) {
                this(
                                model.getNumeroDaQuestao(),
                                model.isAnulada(),
                                model.isTemAssercoes(),
                                model.getEnunciado(),
                                model.getOpcaoCorreta(),
                                Optional
                                                .ofNullable(model.getTextosDeApoio())
                                                .orElse(Collections.emptyList())
                                                .stream()
                                                .map(TextoDeApoioDto::new)
                                                .collect(Collectors.toList()),
                                Optional
                                                .ofNullable(model.getRespostas())
                                                .map(RespostasDto::new)
                                                .orElse(null),
                                Optional
                                                .ofNullable(model.getEnunciadoAssercoes())
                                                .map(EnunciadoAssercoesDto::new)
                                                .orElse(null));
        }

        public Questao toModel() {
                Questao model = new Questao();

                model.setNumeroDaQuestao(this.numeroDaQuestao);
                model.setAnulada(this.anulada);
                model.setTemAssercoes(this.temAssercoes);
                model.setEnunciado(this.enunciado);
                model.setOpcaoCorreta(this.opcaoCorreta);
                model.setTextosDeApoio(
                                Optional
                                                .ofNullable(this.textosDeApoioDto)
                                                .orElse(Collections.emptyList())
                                                .stream()
                                                .map(TextoDeApoioDto::toModel)
                                                .collect(Collectors.toList()));

                model.setRespostas(
                                Optional
                                                .ofNullable(this.respostasDto)
                                                .map(RespostasDto::toModel)
                                                .orElse(null));

                model.setEnunciadoAssercoes(
                                Optional
                                                .ofNullable(this.enunciadoAssercoesDto)
                                                .map(EnunciadoAssercoesDto::toModel)
                                                .orElse(null));

                return model;
        }

}