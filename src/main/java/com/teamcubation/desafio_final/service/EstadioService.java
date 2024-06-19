package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.EstadioDto;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.model.Estadio;

import java.util.List;
import java.util.Optional;

public interface EstadioService {
    Estadio salvar(Estadio estadio);

    List<Estadio> listarEstadios();

    Optional<Estadio> buscarPorId(Long id);
    Boolean buscarEstadiopeloNomeEEstado(String nomeDoEstadio, String siglaEstado);
}
