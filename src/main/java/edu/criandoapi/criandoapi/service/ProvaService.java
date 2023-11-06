package edu.criandoapi.criandoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.criandoapi.criandoapi.domain.model.Prova;
import edu.criandoapi.criandoapi.domain.model.Questao;
import edu.criandoapi.criandoapi.domain.repository.ProvaRepository;
import edu.criandoapi.criandoapi.service.exception.BusinessException;
import edu.criandoapi.criandoapi.service.exception.NotFoundException;

@Service
public class ProvaService {

    @Autowired
    ProvaRepository provaRepository;

    public Prova create(Prova provaToCreate) {
        Optional
                .ofNullable(provaToCreate)
                .orElseThrow(
                        () -> new BusinessException("Prova to create must not be null"));

        // Outros se quiser...

        return this.provaRepository.save(provaToCreate);
    }

    public List<Prova> findAll() {
        return this.provaRepository.findAll();
    }

    public Prova findById(Long id) {

        return this.provaRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Prova updateProva(Long id, Prova provaToUpdate) {
        Prova dbProva = this.findById(id);

        if (!provaToUpdate.getId().equals(id)) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbProva.setNome(provaToUpdate.getNome());
        dbProva.setAnoDeAplicacao(provaToUpdate.getAnoDeAplicacao());
        dbProva.setFonte(provaToUpdate.getFonte());
        dbProva.setQuestoes(provaToUpdate.getQuestoes());

        return this.provaRepository.save(dbProva);
    }

    // Questão Service
    public List<Questao> getAllQuestoes(Long provaId) {
        Prova prova = this.findById(provaId);

        return prova.getQuestoes();
    }

    public Optional<Questao> getQuestaoById(Long provaId, int numeroDaQuestao) {
        Prova prova = this.findById(provaId);

        Optional<Questao> questao = prova.getQuestoes().stream()
                .filter(q -> q.getNumeroDaQuestao() == (numeroDaQuestao)).findFirst();

        return questao;

    }

    public Questao createQuestao(Long provaId, Questao novaQuestao) {

        Prova prova = this.findById(provaId);

        prova.getQuestoes().add(novaQuestao);

        this.create(prova);

        return novaQuestao;
    }

    public Questao updateQuestao(Long provaId, int numeroDaQuestao, Questao questaoToUpdate) {

        Prova dbProva = this.findById(provaId);

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

    public void deleteProva(Long id) {
        Prova dbProva = this.findById(id);

        if (dbProva != null) {
            this.provaRepository.delete(dbProva);
        }

    }

    public void DeleteQuestaoByProvaId(Long provaId, int numeroDaQuestao) {
        Prova dbProva = this.findById(provaId);

        Questao questaoParaExcluir = dbProva.getQuestoes().stream()
                .filter(q -> q.getNumeroDaQuestao() == numeroDaQuestao).findFirst().orElse(null);

        dbProva.getQuestoes().remove(questaoParaExcluir);

        this.provaRepository.save(dbProva);
    }

}
