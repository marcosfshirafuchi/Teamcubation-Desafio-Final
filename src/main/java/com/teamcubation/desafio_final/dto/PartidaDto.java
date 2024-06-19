package com.teamcubation.desafio_final.dto;

import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.model.Estadio;


import java.time.LocalDateTime;


public record PartidaDto(Clube clubeDaCasa, Clube clubeVisitante,Integer golsDoTimeDaCasa,
                         Integer golsDoTimeVisitante, Estadio estadio, LocalDateTime horarioDaPartida) {
}
