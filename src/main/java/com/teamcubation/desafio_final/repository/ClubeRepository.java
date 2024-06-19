package com.teamcubation.desafio_final.repository;

import com.teamcubation.desafio_final.model.Clube;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClubeRepository extends JpaRepository<Clube, Long> {

    //Verifica se o clube está na base dados e se não tiver não pode fazer a atualização no banco de dados
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Clube c WHERE c.nome = :nomeClube")
    boolean existePeloNome(String nomeClube);

    //verifica se um clube está cadastrar e não permite cadastrou um novo clube com o mesmo no nome no mesmo estado
    @Query("SELECT c FROM Clube c WHERE LOWER(c.nome) = LOWER(:nome) AND c.siglaEstado = :siglaEstado")
    List<Clube> findByNomeAndSiglaEstadoIgnoreCase(@Param("nome") String nome, @Param("siglaEstado") String siglaEstado);

    //Inativar o clube se encontrado
    @Transactional
    @Modifying
    @Query("UPDATE Clube c SET c.nome = :novoNome WHERE c.id = :id AND c.siglaEstado <> :siglaEstado")
    int updateNomeByIdAndDifferentSiglaEstado(Long id, String novoNome, String siglaEstado);

    @Query("SELECT COUNT(p) > 0 FROM Partida p WHERE (p.clubeDaCasa.id = :clubeId OR p.clubeVisitante.id = :clubeId) AND p.horarioDaPartida > :novaDataCriacao")
    boolean existePartidasAposNovaDataCriacao(Long clubeId, LocalDate novaDataCriacao);
//    //Obtem a data de criação do clube
//    @Query("SELECT c.dataDeCriacao FROM Clube c WHERE c.id = :clubeId")
//    Optional<LocalDateTime> findDataCriacaoById(Long clubeId);
}
