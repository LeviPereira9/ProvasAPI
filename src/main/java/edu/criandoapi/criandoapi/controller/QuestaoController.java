package edu.criandoapi.criandoapi.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.criandoapi.criandoapi.controller.dto.QuestaoDto;
import edu.criandoapi.criandoapi.domain.model.Questao;
import edu.criandoapi.criandoapi.service.ProvaService;
import edu.criandoapi.criandoapi.service.QuestaoService;
import edu.criandoapi.criandoapi.service.exception.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/prova/{provaId}/questoes")
public class QuestaoController {

    @Autowired
    ProvaService provaService;

    @Autowired
    QuestaoService questaoService;

    @GetMapping
    public ResponseEntity<List<QuestaoDto>> getQuestoes(@PathVariable Long provaId) {

        var questoes = questaoService.getAllQuestoes(provaId);

        List<QuestaoDto> questoesDto = questoes.stream().map(QuestaoDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(questoesDto);
    }

    @GetMapping("/{numeroDaQuestao}")
    public ResponseEntity<QuestaoDto> buscarQuestaoPorProvaIdENumero(
            @PathVariable Long provaId,
            @PathVariable int numeroDaQuestao) {

        Questao questaoOptional = questaoService.findQuestaoByProvaIdAndNumeroQuestao(provaId, numeroDaQuestao)
                .orElseThrow(NotFoundException::new);

        return ResponseEntity.ok(new QuestaoDto(questaoOptional));
    }

    @PostMapping
    public ResponseEntity<QuestaoDto> createQuestaoProva(@PathVariable Long provaId,
            @RequestBody QuestaoDto novaQuestaoDto) {

        Questao questao = questaoService.createQuestao(provaId, novaQuestaoDto.toModel());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(questao.getNumeroDaQuestao())
                .toUri();

        return ResponseEntity.created(location).body(new QuestaoDto(questao));

    }

    @PutMapping("/{numeroDaQuestao}")
    public ResponseEntity<QuestaoDto> updateQuestaoProva(
            @PathVariable Long provaId,
            @PathVariable int numeroDaQuestao,
            @RequestBody QuestaoDto questaoToUpdate) {

        var questao = questaoService.updateQuestao(provaId, numeroDaQuestao, questaoToUpdate.toModel());

        return ResponseEntity.ok(new QuestaoDto(questao));
    }

    @DeleteMapping("/{numeroDaQuestao}")
    public ResponseEntity<Void> deleteQuestaoProva(
            @PathVariable Long provaId,
            @PathVariable int numeroDaQuestao) {
        questaoService.DeleteQuestaoByProvaId(provaId, numeroDaQuestao);

        return ResponseEntity.noContent().build();
    }

}
