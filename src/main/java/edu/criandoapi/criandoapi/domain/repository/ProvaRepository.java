package edu.criandoapi.criandoapi.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.criandoapi.criandoapi.controller.dto.ProvaSimplesDto;
import edu.criandoapi.criandoapi.domain.model.Prova;
import edu.criandoapi.criandoapi.domain.model.Questao;

public interface ProvaRepository extends JpaRepository<Prova, Long> {

    @Query("SELECT new edu.criandoapi.criandoapi.controller.dto.ProvaSimplesDto(p.id, p.nome) FROM edu.criandoapi.criandoapi.domain.model.Prova p")
    List<ProvaSimplesDto> findAllProvaSimplesDto();

    @Query("SELECT q FROM edu.criandoapi.criandoapi.domain.model.Prova p JOIN p.questoes q WHERE p.id = :provaId AND q.numeroDaQuestao = :numeroDaQuestao")
    Optional<Questao> findQuestaoByProvaIdAndNumero(@Param("provaId") Long provaId,
            @Param("numeroDaQuestao") int numeroDaQuestao);

    @Query("SELECT q FROM edu.criandoapi.criandoapi.domain.model.Prova p JOIN p.questoes q WHERE p.id = :provaId")
    List<Questao> findAllQuestoesByProvaId(@Param("provaId") Long provaId);

}
