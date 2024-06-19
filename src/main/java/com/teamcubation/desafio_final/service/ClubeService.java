package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.ClubeDto;
import com.teamcubation.desafio_final.model.Clube;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ClubeService {
    List<Clube> listarClubes();
    Optional<Clube> buscarPorId(Long id);
    Clube salvar(Clube clube);

    void verificarConflitoClube(Clube clube);
    boolean clubeExiste(String nomeClube);



    void atualizarDataDeCriacao(Long id, LocalDate localDate);

    boolean atualizarNomeClube(Long id, String novoNome, String siglaEstado);

    void inativarClube(Long id);

    Clube editarClube(Long id, ClubeDto clubeDto);

    //LocalDateTime obterDataCriacaoClube(Long id);
}