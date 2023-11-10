package edu.criandoapi.criandoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.criandoapi.criandoapi.domain.model.Prova;
import edu.criandoapi.criandoapi.domain.model.Questao;
import edu.criandoapi.criandoapi.domain.repository.ProvaRepository;
import edu.criandoapi.criandoapi.service.exception.BusinessException;

@Service
public class QuestaoService {

    @Autowired
    ProvaRepository provaRepository;

    @Autowired
    ProvaService provaService;

    // Questão Service --->
    public List<Questao> getAllQuestoes(Long provaId) {
        List<Questao> questoes = provaRepository.findAllQuestoesByProvaId(provaId);

        return questoes;
    }

    public Optional<Questao> findQuestaoByProvaIdAndNumeroQuestao(
            Long provaId,
            int numeroDaQuestao) {
        Optional<Questao> questaoOptional = provaRepository.findQuestaoByProvaIdAndNumero(provaId, numeroDaQuestao);

        return questaoOptional;
    }

    public Questao createQuestao(Long provaId, Questao novaQuestao) {

        Prova prova = provaService.findById(provaId);

        prova.getQuestoes().add(novaQuestao);

        provaService.create(prova);

        return novaQuestao;
    }

    public Questao updateQuestao(Long provaId, int numeroDaQuestao, Questao questaoToUpdate) {

        Prova dbProva = provaService.findById(provaId);

        if (questaoToUpdate.getNumeroDaQuestao() != numeroDaQuestao) {
            throw new BusinessException("Update IDs must be the same.");
        }

        Questao questaoExistente = dbProva
                .getQuestoes()
                .stream()
                .filter(q -> q.getNumeroDaQuestao() == numeroDaQuestao)
                .findFirst()
                .orElse(null);

        if (questaoExistente != null) {
            questaoExistente.setAnulada(questaoToUpdate.isAnulada());
            questaoExistente.setTemAssercoes(questaoExistente.isTemAssercoes());
            questaoExistente.setEnunciado(questaoToUpdate.getEnunciado());
            questaoExistente.setOpcaoCorreta(questaoToUpdate.getOpcaoCorreta());
            questaoExistente.setTextosDeApoio(questaoToUpdate.getTextosDeApoio());
            questaoExistente.setRespostas(questaoToUpdate.getRespostas());
            questaoExistente.setEnunciadoAssercoes(questaoToUpdate.getEnunciadoAssercoes());

            this.provaRepository.save(dbProva);
        }

        return questaoExistente;
    }

    public void DeleteQuestaoByProvaId(Long provaId, int numeroDaQuestao) {
        Prova dbProva = provaService.findById(provaId);

        Questao questaoParaExcluir = dbProva.getQuestoes().stream()
                .filter(q -> q.getNumeroDaQuestao() == numeroDaQuestao).findFirst().orElse(null);

        dbProva.getQuestoes().remove(questaoParaExcluir);

        this.provaRepository.save(dbProva);
    }

}
