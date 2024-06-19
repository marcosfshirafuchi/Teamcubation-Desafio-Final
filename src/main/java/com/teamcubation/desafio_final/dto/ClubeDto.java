package com.teamcubation.desafio_final.dto;

import java.time.LocalDate;

public record ClubeDto(String nomeDoClube,
                       String siglaEstado,
        LocalDate dataDeCriacao,
        Boolean status) {

}
