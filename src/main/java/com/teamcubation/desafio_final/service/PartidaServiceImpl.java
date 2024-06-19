package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.exception.DadosInvalidosException;
import com.teamcubation.desafio_final.model.Partida;
import com.teamcubation.desafio_final.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartidaServiceImpl implements PartidaService{

    private final PartidaRepository partidaRepository;


    private ClubeService clubeService;

    public PartidaServiceImpl(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    @Override
    public Partida cadastrar(Partida partida) {
        return this.partidaRepository.save(partida);
    }

//    @Override
//    public Boolean validarDataPartidaParaCadastro(PartidaDto request) {
//        // Obter a data de criação do clube mandante
//        LocalDateTime dataCriacaoMandante = clubeService.obterDataCriacaoClube(request.clubeDaCasa().getId());
//
//        LocalDateTime dataCriacaoVisitante = clubeService.obterDataCriacaoClube(request.clubeVisitante().getId());
//        validarDataPartida(request.horarioDaPartida(), dataCriacaoVisitante);
//
//        if (validarDataPartida(request.horarioDaPartida(), dataCriacaoMandante) == true || validarDataPartida(request.horarioDaPartida(), dataCriacaoVisitante) == true) {
//            return true;
//        } else {
//            return false;
//        }

        // Obter a data de criação do clube visitante (se existir)
//        if (request.getVisitanteId() != null) {
//            LocalDateTime dataCriacaoVisitante = clubeService.getDataCriacaoClube(request.getVisitanteId());
//            validarDataPartida(request.getDataPartida(), dataCriacaoVisitante);
//        }
   // }

//    @Override
//    public List<Partida> listarPartidas() {
//        return this.partidaRepository.findAll();
//    }

//    @Override
//    public Partida cadastrarPartida(PartidaDto partida) {
//        validarDadosPartida(partida);
//        return partidaRepository.save(partida);
//    }

//    @Override
//    public Partida atualizarPartida(Long id, PartidaDto partida) {
//        return null;
//    }

//    private Boolean validarDataPartida(LocalDateTime dataPartida, LocalDateTime dataCriacaoClube) {
//        // Verificar se a data da partida é anterior à data de criação do clube
//        if (dataPartida.isBefore(dataCriacaoClube)) {
//            return true;
//            //throw new DataInvalidaException("Data da partida não pode ser anterior à data de criação do clube.");
//        }
//        return false;
//    }
//    @Override
//    public Partida cadastrarPartida(Partida partida) {
//          return this.partidaRepository.save(partida);
//    }
//    @Override
//    public Partida atualizarPartida(Long id, Partida partida) {
//        Partida partidaExistente = partidaRepository.findById(id)
//                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida não encontrada"));
//
//        partida.setId(partidaExistente.getId());
//        validarDadosPartida(partida);
//        return partidaRepository.save(partida);
//    }

//    private void validarDadosPartida(Partida partida) {
//        if (partida.getHorarioDaPartida() != null && partida.getHorarioDaPartida().isBefore(LocalDateTime.now())) {
//            throw new DadosInvalidosException("A data e hora da partida não pode estar no passado");
//        }
//
//
//    }
}