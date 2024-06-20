package com.teamcubation.desafio_final.service;

import com.teamcubation.desafio_final.dto.EstadioDto;
import com.teamcubation.desafio_final.exception.ConflitoDadosException;
import com.teamcubation.desafio_final.exception.NotFoundException;
import com.teamcubation.desafio_final.model.Estadio;
import com.teamcubation.desafio_final.repository.EstadioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstadioServiceImpl implements EstadioService{
    private final EstadioRepository estadioRepository;


    public EstadioServiceImpl(EstadioRepository estadioRepository) {
        this.estadioRepository = estadioRepository;
    }

    @Override
    public Estadio salvar(Estadio estadio) {
        if (estadioRepository.existeEstadioComOMesmoNome(estadio.getNomeDoEstadio(), estadio.getSiglaEstado())) {
            throw new RuntimeException("Stadium with the same name in the state already exists");
        }
        verificarConflitoEstadio(estadio);
        return this.estadioRepository.save(estadio);
    }

    @Override
    public List<Estadio> listarEstadios() {
        return this.estadioRepository.findAll();
    }

    @Override
    public Optional<Estadio> buscarPorId(Long id) {
        return this.estadioRepository.findById(id);
    }


    @Override
    public Estadio editar(Long id,EstadioDto estadioDto) {
        Optional<Estadio> optionalEstadio = estadioRepository.findById(id);
        if (optionalEstadio.isPresent()) {
            Estadio estadio = optionalEstadio.get();

            // Atualizar os dados do clube
            estadio.setNomeDoEstadio(estadioDto.nomeDoEstadio());
            estadio.setSiglaEstado(estadioDto.siglaEstado());

            // Salvar no banco de dados
            return estadioRepository.save(estadio);
        } else {
            throw new NotFoundException("Estadio não encontrado com o ID: " + id);
        }
    }

    @Override
    public void verificarConflitoEstadio(Estadio estadio) {
        Optional<Estadio> estadiosExistentes = estadioRepository.existsByNomeDoEstadio(estadio.getNomeDoEstadio());
        if (!estadiosExistentes.isEmpty()) {
            throw new ConflitoDadosException("Já existe um estadio com o mesmo nome neste estado");
        }
    }


}
