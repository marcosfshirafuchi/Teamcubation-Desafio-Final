package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.ClubeDto;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.service.ClubeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/clubes")
public class ClubeController {
    public final ClubeService clubeService;
    static String uf[] = {"AL", "AP", "AM", "BA",
            "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG",
            "PA", "PB", "PR", "PE",
            "PI", "RJ", "RN", "RS",
            "RO", "RR", "SC", "SP",
            "SE", "TO"};

    public ClubeController(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClubeDto clubeDto) {
        try {
            Clube clube = new Clube();
            LocalDate hoje = LocalDate.now();


            if (clubeDto.nomeDoClube() == "" || clubeDto.siglaEstado() == "" || clubeDto.dataDeCriacao() == null || clubeDto.nomeDoClube().length() < 2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
            }

            boolean estadoBrasileiroProcurado = false;
            for (int i = 0; i < uf.length; i++) {
                if (clubeDto.siglaEstado().equals(uf[i])) {
                    estadoBrasileiroProcurado = true;
                    break;
                }
            }
            if (estadoBrasileiroProcurado == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado inválido!");
            }
            if (clubeDto.dataDeCriacao().isAfter(hoje)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode cadastrar data futura!");
            }
            clube.setNomeDoClube(clubeDto.nomeDoClube());
            clube.setSiglaEstado(clubeDto.siglaEstado());
            clube.setDataDeCriacao(clubeDto.dataDeCriacao());
            clube.setStatus(clubeDto.status());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.clubeService.salvar(clube));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um clube com o mesmo nome neste estado!");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClubeDto clubeDto) {
        try {
            Optional<Clube> clubeExiste = this.clubeService.buscarPorId(id);
            LocalDate hoje = LocalDate.now();
            if (clubeExiste.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clube não encontrado com o ID: " + id);
            }
            if (clubeDto.nomeDoClube().length() <2) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O nome do clude deve ter mais de duas letras!");
            }
            boolean estadoBrasileiroProcurado = false;
            for (int i = 0; i < uf.length; i++) {
                if (clubeDto.siglaEstado().equals(uf[i])) {
                    estadoBrasileiroProcurado = true;
                    break;
                }
            }
            if (estadoBrasileiroProcurado == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado inválido!");
            }
            if (clubeDto.dataDeCriacao().isAfter(hoje)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode cadastrar data futura!");
            }
            Clube clube = clubeService.editarClube(id, clubeDto);
            return ResponseEntity.status(HttpStatus.OK).body(clube);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um clube com o mesmo nome neste estado!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> inativarClube(@PathVariable Long id) {
        try {
            clubeService.inativarClube(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe clube para ser inativado!");
        }
    }

    @GetMapping
    public ResponseEntity<List<Clube>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.clubeService.listarClubes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Clube> optClube = this.clubeService.buscarPorId(id);
        if (optClube.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não registro de clube por esse id: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optClube.get());
    }

}
