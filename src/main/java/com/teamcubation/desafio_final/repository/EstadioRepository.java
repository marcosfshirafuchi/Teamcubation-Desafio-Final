package com.teamcubation.desafio_final.repository;

import com.teamcubation.desafio_final.dto.EstadioDto;
import com.teamcubation.desafio_final.model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
    //Verifica se já existe cadastrado e não permite cadastra com o mesmo nome
    @Query("SELECT COUNT(e) > 0 FROM Estadio e WHERE e.nomeDoEstadio = :nomeDoEstadio AND e.siglaEstado = :siglaEstado")
    boolean existeEstadioComOMesmoNome(@Param("nomeDoEstadio") String nomeDoEstadio, @Param("siglaEstado") String siglaEstado);

    //Verifica se o estádio existe
    @Query("SELECT e FROM Estadio e WHERE e.nomeDoEstadio = :nomeDoEstadio AND e.siglaEstado = :siglaEstado")
    Optional<EstadioDto> findByNameAndState(String nomeDoEstadio, String siglaEstado);

    //Verifica o estadio pelo nome e não permite cadastrar
    Optional<Estadio> existsByNomeDoEstadio(String nomeDoEstadio);

}
