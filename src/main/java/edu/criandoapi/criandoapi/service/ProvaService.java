package edu.criandoapi.criandoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.criandoapi.criandoapi.controller.dto.ProvaSimplesDto;
import edu.criandoapi.criandoapi.domain.model.Prova;
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

    public void deleteProva(Long id) {
        Prova dbProva = this.findById(id);

        if (dbProva != null) {
            this.provaRepository.delete(dbProva);
        }

    }

    // Pegar todas as provas com informações simples.
    public List<ProvaSimplesDto> getAllProvasSimples() {
        List<ProvaSimplesDto> provas = provaRepository.findAllProvaSimplesDto();

        return provas;
    }

}
