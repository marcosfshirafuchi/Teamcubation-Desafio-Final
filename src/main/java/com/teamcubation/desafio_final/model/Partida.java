package com.teamcubation.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Partida {
    //private static final long serialVersion = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "clube_da_casa_id", nullable = false)
    private Clube clubeDaCasa;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "clube_visitante_id", nullable = false)
    private Clube clubeVisitante;

    private Integer golsDoTimeDaCasa;

    private Integer golsDoTimeVisitante;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "estadio_id", nullable = false)
    private Estadio estadio;

    private LocalDateTime horarioDaPartida;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clube getClubeDaCasa() {
        return clubeDaCasa;
    }

    public void setClubeDaCasa(Clube clubeDaCasa) {
        this.clubeDaCasa = clubeDaCasa;
    }

    public Clube getClubeVisitante() {
        return clubeVisitante;
    }

    public void setClubeVisitante(Clube clubeVisitante) {
        this.clubeVisitante = clubeVisitante;
    }

    public Integer getGolsDoTimeDaCasa() {
        return golsDoTimeDaCasa;
    }

    public void setGolsDoTimeDaCasa(Integer golsDoTimeDaCasa) {
        this.golsDoTimeDaCasa = golsDoTimeDaCasa;
    }

    public Integer getGolsDoTimeVisitante() {
        return golsDoTimeVisitante;
    }

    public void setGolsDoTimeVisitante(Integer golsDoTimeVisitante) {
        this.golsDoTimeVisitante = golsDoTimeVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public LocalDateTime getHorarioDaPartida() {
        return horarioDaPartida;
    }

    public void setHorarioDaPartida(LocalDateTime horarioDaPartida) {
        this.horarioDaPartida = horarioDaPartida;
    }

    // Método para retornar o ID do Clube da Casa
    public Long getClubeDaCasaId() {
        return clubeDaCasa != null ? clubeDaCasa.getId() : null;
    }

    // Método para retornar o ID do Clube Visitante
    public Long getClubeVisitanteId() {
        return clubeVisitante != null ? clubeVisitante.getId() : null;
    }
}
