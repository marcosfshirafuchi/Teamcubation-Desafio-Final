package com.teamcubation.desafio_final.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table
public class Estadio{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    @Size(min = 3, max=255, message = "O nome do estádio deve ter entre 3 e 255 caracteres")
    private String nomeDoEstadio;
    @Column(nullable = false, length = 2)
    private String siglaEstado;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "estadio", fetch = FetchType.LAZY)
    private List<Partida> partidasEstadio = new ArrayList<>();

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeDoEstadio() {
        return nomeDoEstadio;
    }

    public void setNomeDoEstadio(String nomeDoEstadio) {
        this.nomeDoEstadio = nomeDoEstadio;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}
