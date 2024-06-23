package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.ClubeDto;
import com.teamcubation.desafio_final.exception.ConflitoDadosException;
import com.teamcubation.desafio_final.exception.DataInvalidaException;
import com.teamcubation.desafio_final.exception.NotFoundException;
import com.teamcubation.desafio_final.exception.ResourceNotFoundException;
import com.teamcubation.desafio_final.model.Clube;
import com.teamcubation.desafio_final.repository.ClubeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ClubeServiceImpl implements ClubeService{
    private final ClubeRepository clubeRepository;

    public ClubeServiceImpl(ClubeRepository clubeRepository) {
        this.clubeRepository = clubeRepository;
    }

    @Override
    public List<Clube> listarClubes() {
        return this.clubeRepository.findAll();
    }

    @Override
    public Optional<Clube> buscarPorId(Long id) {
        return this.clubeRepository.findById(id);
    }

    @Override
    public Clube salvar(Clube clube) {
        verificarConflitoClube(clube);
        if (clubeRepository.existsByNomeAndDifferentSiglaEstado(clube.getNome(), clube.getSiglaEstado())) {
            throw new RuntimeException("Já existe um clube com o mesmo nome em mesma siglaEstado!");
        }
        return this.clubeRepository.save(clube);
    }
    @Override
    public void verificarConflitoClube(Clube clube) {
        List<Clube> clubesExistentes = clubeRepository.findByNomeAndSiglaEstadoIgnoreCase(clube.getNome(), clube.getSiglaEstado());
        if (!clubesExistentes.isEmpty()) {
            throw new ConflitoDadosException("Já existe um clube com o mesmo nome neste estado");
        }
    }

    @Override
    public void inativarClube(Long id) {
        Optional<Clube> optionalClube = clubeRepository.findById(id);
        if (optionalClube.isPresent()) {
            Clube clube = optionalClube.get();
            clube.setStatus(false); // Inativa o clube
            clubeRepository.save(clube);
        }else{
            throw new NotFoundException("Clube não encontrado com o ID: " + id);
        }
    }

    @Override
    public Clube editarClube(Long id, ClubeDto clubeDto) {
        Optional<Clube> optionalClube = clubeRepository.findById(id);
        if (optionalClube.isPresent()) {
            Clube clube = optionalClube.get();

//            // Validar os dados atualizados
//            validarDadosAtualizados(clube, clubeDto);

            // Atualizar os dados do clube
            clube.setNomeDoClube(clubeDto.nomeDoClube());
            clube.setSiglaEstado(clubeDto.siglaEstado());
            clube.setDataDeCriacao(clubeDto.dataDeCriacao());

            // Salvar no banco de dados
            return clubeRepository.save(clube);
        } else {
            throw new NotFoundException("Clube não encontrado com o ID: " + id);
        }
    }

    @Override
    public Clube findById(Long id) {
        Optional<Clube> clubeOptional = clubeRepository.findById(id);
        return clubeOptional.orElseThrow(() -> new ResourceNotFoundException("Clube não encontrado com o id: " + id));
    }

    @Override
    public void atualizarDataDeCriacao(Long id, LocalDate novaDataCriacao) {
        boolean existemPartidasAposNovaData = clubeRepository.existePartidasAposNovaDataCriacao(id, novaDataCriacao);
        if (existemPartidasAposNovaData) {
            throw new DataInvalidaException("Não é possível atualizar a data de criação porque existem partidas registradas após essa data.");
        }
    }

}
