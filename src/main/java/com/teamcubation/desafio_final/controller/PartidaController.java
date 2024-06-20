package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Partida;
import com.teamcubation.desafio_final.service.PartidaService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/partidas")
public class PartidaController {

    private PartidaService partidaService;


    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody PartidaDto partidaDto) {
        try {
            Partida partida = new Partida();
            LocalDateTime agora = LocalDateTime.now();
        if (partidaDto.clubeVisitante().getId()==null||partidaDto.clubeDaCasa().getId()==null||
                partidaDto.golsDoTimeDaCasa()==null ||partidaDto.golsDoTimeVisitante() == null ||
                partidaDto.estadio().getId()==null||partidaDto.horarioDaPartida().equals("")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
        }
        if (partidaDto.golsDoTimeDaCasa()<0 ||partidaDto.golsDoTimeVisitante()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
        }
        if (partidaDto.clubeDaCasa().getId()==(partidaDto.clubeVisitante().getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os ids do clube da casa e do clube visitante tem que serem diferentes");
        }

            partida.setClubeDaCasa(partidaDto.clubeDaCasa());
            partida.setClubeVisitante(partidaDto.clubeVisitante());
            partida.setEstadio(partidaDto.estadio());
            partida.setGolsDoTimeDaCasa(partidaDto.golsDoTimeDaCasa());
            partida.setGolsDoTimeVisitante(partidaDto.golsDoTimeVisitante());
            partida.setHorarioDaPartida(partidaDto.horarioDaPartida());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.partidaService.cadastrar(partida));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um clube com o mesmo nome neste estado!");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editarPartida(@PathVariable Long id, @Valid @RequestBody PartidaDto partidaDto) {
        try {
           Optional<Partida> partidaExiste = this.partidaService.buscarPartidaPorId(id);
            if (partidaExiste.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partida não encontrada com o ID: " + id);
            }
            Partida partida = partidaService.atualizarPartida(id, partidaDto);
            return ResponseEntity.status(HttpStatus.OK).body(partida);
        }catch (RuntimeException | BadRequestException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Estadio já possui jogo!");
        }
    }
        @DeleteMapping("/{id}")
        public ResponseEntity<?> removerUmaPartida (@PathVariable Long id){
            try {
                partidaService.removerPartida(id);
                return ResponseEntity.noContent().build();
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe partida para ser removida por esse id: " + id);
            }
        }
        @GetMapping("/{id}")
        public ResponseEntity<Object> buscarPartidaPorId (@PathVariable Long id){
            Optional<Partida> optPartida = this.partidaService.buscarPartidaPorId(id);
            if (optPartida.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não registro da partida por esse id: " + id);
            }
            return ResponseEntity.status(HttpStatus.OK).body(optPartida.get());
        }

        @GetMapping
        public ResponseEntity<List<Partida>> listarPartidas () {
            List<Partida> partidas = partidaService.listarTodasAsPartidas();
            return ResponseEntity.ok(partidas);
        }

    }
