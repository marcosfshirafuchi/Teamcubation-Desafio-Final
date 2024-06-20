package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Partida;
import org.apache.coyote.BadRequestException;
import java.util.List;
import java.util.Optional;

public interface PartidaService {
    Partida cadastrar(Partida partida);

    List<Partida> listarTodasAsPartidas();

    Optional<Partida> buscarPartidaPorId(Long id);

    void removerPartida(Long id);

    Partida atualizarPartida(Long id, PartidaDto partidaDto) throws BadRequestException;

}
