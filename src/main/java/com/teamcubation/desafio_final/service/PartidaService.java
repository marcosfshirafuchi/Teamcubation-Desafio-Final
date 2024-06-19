package com.teamcubation.desafio_final.service;


import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Partida;

import java.util.List;
import java.util.Optional;

public interface PartidaService {
    Partida cadastrar(Partida partida);

    //Boolean validarDataPartidaParaCadastro(PartidaDto partidaDto);

    List<Partida> listarTodasAsPartidas();

    Optional<Partida> buscarPartidaPorId(Long id);

    void removerPartida(Long id);

    // Partida cadastrarPartida(Partida partida);

    //Partida atualizarPartida(Long id, PartidaDto partida);
}
