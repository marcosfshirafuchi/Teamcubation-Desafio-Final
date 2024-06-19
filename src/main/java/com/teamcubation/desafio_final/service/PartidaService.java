package com.teamcubation.desafio_final.service;


import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Partida;

import java.util.List;

public interface PartidaService {
    Partida cadastrar(Partida partida);

    //Boolean validarDataPartidaParaCadastro(PartidaDto partidaDto);

   // List<Partida> listarPartidas();

   // Partida cadastrarPartida(Partida partida);

    //Partida atualizarPartida(Long id, PartidaDto partida);
}
