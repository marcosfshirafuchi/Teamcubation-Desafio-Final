package com.teamcubation.desafio_final.repository;

import com.teamcubation.desafio_final.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
}
