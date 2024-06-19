package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.model.Partida;
import com.teamcubation.desafio_final.service.ClubeService;
import com.teamcubation.desafio_final.service.EstadioService;
import com.teamcubation.desafio_final.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private EstadioService estadioService;
    private PartidaService partidaService;
    private ClubeService clubeService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody PartidaDto partidaDto) {
        Partida partida = new Partida();
        LocalDateTime agora = LocalDateTime.now();
        //partidaService.validarDataPartidaParaCadastro(partidaDto);

//        if (partidaDto.clubeVisitante().getNome()==null||partidaDto.clubeDaCasa().getNome()==null||
//                partidaDto.golsDoTimeDaCasa()==null ||partidaDto.golsDoTimeVisitante() == null ||
//                partidaDto.estadio().getNomeDoEstadio()==""||partidaDto.horarioDaPartida().equals(null)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
//        }
//        if (partidaDto.golsDoTimeDaCasa()<0 ||partidaDto.golsDoTimeVisitante()<0){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
//        }
//        if (partidaDto.horarioDaPartida().isAfter(agora)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
//        }
//        if (partidaDto.clubeDaCasa().equals(partidaDto.clubeVisitante())||partidaDto.clubeVisitante().equals(partidaDto.clubeDaCasa())){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados inválidos");
//        }
//        if (estadioService.buscarEstadiopeloNomeEEstado(partidaDto.estadio().getNomeDoEstadio(),partidaDto.estadio().getSiglaEstado())==true){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O estádio não existe");
//        }
//        boolean existeClubeDaCasa = clubeService.clubeExiste(partidaDto.clubeDaCasa().getNome());
//        if (existeClubeDaCasa==false){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nome do clube da casa!");
//        }
//        boolean existeClubeVisitante = clubeService.clubeExiste(partidaDto.clubeVisitante().getNome());
//        if (existeClubeVisitante==false){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe nome do clube visitante!");
//        }
//        if (partidaService.validarDataPartidaParaCadastro(partidaDto) == true){
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Data da partida não pode ser anterior à data de criação do clube.");
//        }
        partida.setClubeDaCasa(partidaDto.clubeDaCasa());
        partida.setClubeVisitante(partidaDto.clubeVisitante());
        partida.setEstadio(partidaDto.estadio());
        partida.setGolsDoTimeDaCasa(partidaDto.golsDoTimeDaCasa());
        partida.setGolsDoTimeVisitante(partida.getGolsDoTimeVisitante());
        partida.setHorarioDaPartida(partidaDto.horarioDaPartida());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.partidaService.cadastrar(partida));
    }

//    @GetMapping
//    public ResponseEntity<List<Partida>> findAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(this.partidaService.listarPartidas());
//    }



//        @PostMapping
//        public ResponseEntity<?> cadastrarPartida(@Valid @RequestBody PartidaDto partidaDto) {
//
//            Partida partida = new Partida();
//            partida.setClubeDaCasa(partidaDto.clubeDaCasa());
//            partida.setClubeVisitante(partidaDto.clubeVisitante());
//            partida.setGolsDoTimeDaCasa(partidaDto.golsDoTimeDaCasa());
//            partida.setGolsDoTimeVisitante(partidaDto.golsDoTimeVisitante());
//            partida.setHorarioDaPartida(partidaDto.horarioDaPartida());
//            partida.setEstadio(partidaDto.estadio());
//            return ResponseEntity.status(HttpStatus.CREATED).body(partidaService.cadastrarPartida(partida));
//        }

//        @PutMapping("/{id}")
//        public ResponseEntity<?> atualizarPartida(@PathVariable Long id, @Valid @RequestBody PartidaDto partida) {
//            Partida partidaAtualizada = partidaService.atualizarPartida(id, partida);
//            return ResponseEntity.ok(partidaAtualizada);
//        }

//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> removerPartida(@PathVariable Long id) {
//            partidaService.removerPartida(id);
//            return ResponseEntity.noContent().build();
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<Partida> buscarPartidaPorId(@PathVariable Long id) {
//            Partida partida = partidaService.buscarPartidaPorId(id);
//            return ResponseEntity.ok(partida);
//        }
//
//        @GetMapping
//        public ResponseEntity<Page<Partida>> listarTodasAsPartidas(Pageable pageable) {
//            Page<Partida> partidas = partidaService.listarTodasAsPartidas(pageable);
//            return ResponseEntity.ok(partidas);
//        }

    }
