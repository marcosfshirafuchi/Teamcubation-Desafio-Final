package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.ClubeDto;
import com.teamcubation.desafio_final.dto.EstadioDto;
import com.teamcubation.desafio_final.exception.ConflitoDadosException;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.model.Estadio;
import com.teamcubation.desafio_final.service.EstadioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estadios")
public class EstadioController {
    private EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUmEstadio(@RequestBody EstadioDto estadioDto) {
        try {
            Estadio estadio = new Estadio();
            if (estadioDto.nomeDoEstadio() == "" || estadioDto.siglaEstado() == "" || estadioDto.nomeDoEstadio().length() < 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
            }
            estadio.setNomeDoEstadio(estadioDto.nomeDoEstadio());
            estadio.setSiglaEstado(estadioDto.siglaEstado());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.estadioService.salvar(estadio));
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um estádio com o mesmo nome cadastrado!");
        }
    }

    @GetMapping
    public ResponseEntity<List<Estadio>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.estadioService.listarEstadios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Estadio> optEstadio = this.estadioService.buscarPorId(id);
        if (optEstadio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não registro do estádio por esse id: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optEstadio.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody EstadioDto estadioDto) {
        try{
            Estadio estadio = new Estadio();
            if (estadioDto.nomeDoEstadio().length() < 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
            }
            if (estadioService.buscarEstadiopeloNomeEEstado(estadioDto.nomeDoEstadio(),estadioDto.siglaEstado())==true){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O estádio não existe");
            }
            estadio.setNomeDoEstadio(estadioDto.nomeDoEstadio());
            estadio.setSiglaEstado(estadioDto.siglaEstado());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.estadioService.salvar(estadio));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um estádio com o mesmo nome cadastrado!");
        }
    }

}
