package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.EstadioDto;
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
    static String uf[] = {"AL", "AP", "AM", "BA",
            "CE", "DF", "ES", "GO",
            "MA", "MT", "MS", "MG",
            "PA", "PB", "PR", "PE",
            "PI", "RJ", "RN", "RS",
            "RO", "RR", "SC", "SP",
            "SE", "TO"};


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
            boolean estadoBrasileiroProcurado = false;
            for (int i = 0; i < uf.length; i++) {
                if (estadioDto.siglaEstado().equals(uf[i])) {
                    estadoBrasileiroProcurado = true;
                    break;
                }
            }
            if (estadoBrasileiroProcurado == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado inválido!");
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
            Optional<Estadio> estadioExiste = this.estadioService.buscarPorId(id);
            if (estadioExiste.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estadio não encontrado com o ID: " + id);
            }
            Estadio estadio = new Estadio();
            if (estadioDto.nomeDoEstadio().length() < 3) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
            }
            estadio.setNomeDoEstadio(estadioDto.nomeDoEstadio());
            estadio.setSiglaEstado(estadioDto.siglaEstado());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.estadioService.editar(id,estadioDto));
        }catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um estádio com o mesmo nome cadastrado!");
        }
    }

}
