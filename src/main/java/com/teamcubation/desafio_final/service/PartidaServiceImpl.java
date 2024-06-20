package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.PartidaDto;
import com.teamcubation.desafio_final.exception.NotFoundException;
import com.teamcubation.desafio_final.model.Partida;
import com.teamcubation.desafio_final.repository.EstadioRepository;
import com.teamcubation.desafio_final.repository.PartidaRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaServiceImpl implements PartidaService{

    private final PartidaRepository partidaRepository;
    private final EstadioRepository estadioRepository;

    private ClubeService clubeService;

    public PartidaServiceImpl(PartidaRepository partidaRepository, EstadioRepository estadioRepository) {
        this.partidaRepository = partidaRepository;
        this.estadioRepository = estadioRepository;
    }

    @Override
    public Partida cadastrar(Partida partida) {
        return this.partidaRepository.save(partida);
    }

    @Override
    public List<Partida> listarTodasAsPartidas() {
        return this.partidaRepository.findAll();
    }

    @Override
    public Optional<Partida> buscarPartidaPorId(Long id) {
        return this.partidaRepository.findById(id);
    }

    @Override
    public void removerPartida(Long id) {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);
        if (optionalPartida.isPresent()) {
            partidaRepository.deleteById(id);
        }else{
            throw new NotFoundException("Partida não encontrada com o ID: " + id);
        }
    }

    @Override
    public Partida atualizarPartida(Long id, PartidaDto partidaDto) throws BadRequestException {
        Optional<Partida> optionalPartida = partidaRepository.findById(id);
        if (optionalPartida.isEmpty()) {
            throw new NotFoundException("Partida não encontrada com o ID: " + id);
        }

        Partida partida = optionalPartida.get();
        partida.setClubeDaCasa(clubeService.findById(partidaDto.clubeDaCasa().getId()));
        partida.setClubeVisitante(clubeService.findById(partidaDto.clubeVisitante().getId()));
        partida.setGolsDoTimeDaCasa(partidaDto.golsDoTimeDaCasa());
        partida.setGolsDoTimeVisitante(partidaDto.golsDoTimeVisitante());
        partida.setEstadio(estadioRepository.findById(partidaDto.estadio().getId())
                .orElseThrow(() -> new BadRequestException("Estádio não encontrado.")));
        partida.setHorarioDaPartida(partidaDto.horarioDaPartida());

        return partidaRepository.save(partida);
    }

}