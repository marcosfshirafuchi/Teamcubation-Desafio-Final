package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.ClubeDto;
import com.teamcubation.desafio_final.model.Clube;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClubeService {
    List<Clube> listarClubes();
    Optional<Clube> buscarPorId(Long id);
    Clube salvar(Clube clube);
    void verificarConflitoClube(Clube clube);
    void atualizarDataDeCriacao(Long id, LocalDate localDate);
    void inativarClube(Long id);
    Clube editarClube(Long id, ClubeDto clubeDto);
    Clube findById(Long id);

    //LocalDateTime obterDataCriacaoClube(Long id);
}
