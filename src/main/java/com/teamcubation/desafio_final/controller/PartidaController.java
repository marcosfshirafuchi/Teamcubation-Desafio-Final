package com.teamcubation.desafio_final.controller;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.model.Estadio;
import com.teamcubation.desafio_final.model.Partida;
import com.teamcubation.desafio_final.service.ClubeService;
import com.teamcubation.desafio_final.service.EstadioService;
import com.teamcubation.desafio_final.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        partida.setGolsDoTimeVisitante(partidaDto.golsDoTimeVisitante());
        partida.setHorarioDaPartida(partidaDto.horarioDaPartida());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.partidaService.cadastrar(partida));
    }



//        @PutMapping("/{id}")
//        public ResponseEntity<?> atualizarPartida(@PathVariable Long id, @Valid @RequestBody PartidaDto partida) {
//            Partida partidaAtualizada = partidaService.atualizarPartida(id, partida);
//            return ResponseEntity.ok(partidaAtualizada);
//        }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerUmaPartida(@PathVariable Long id) {
        try {
            partidaService.removerPartida(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe partida para ser removida por esse id: " + id);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPartidaPorId(@PathVariable Long id) {
        Optional<Partida> optPartida = this.partidaService.buscarPartidaPorId(id);
        if (optPartida.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não registro da partida por esse id: " + id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optPartida.get());
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listarPartidas() {
        List<Partida> partidas = partidaService.listarTodasAsPartidas();
        return ResponseEntity.ok(partidas);
    }

}
