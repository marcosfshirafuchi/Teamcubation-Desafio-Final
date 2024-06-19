package com.teamcubation.desafio_final.service;


import com.teamcubation.desafio_final.dto.EstadioDto;
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
            // Ou você pode criar uma exceção personalizada para representar o conflito
            // throw new StadiumAlreadyExistsException(stadium.getName(), stadium.getState());
        }

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
    public Boolean buscarEstadiopeloNomeEEstado(String nomeDoEstadio, String siglaEstado) {
        Optional<EstadioDto> stadiumOptional = estadioRepository.findByNameAndState(nomeDoEstadio, siglaEstado);

        if (stadiumOptional.isEmpty()) {
            return true;
//            throw new RuntimeException("Stadium not found with name " + nomeDoEstadio + " in state " + siglaEstado);
//            // Ou você pode criar uma exceção personalizada para representar o 404
//            // throw new StadiumNotFoundException(name, state);
        }
        return false;


    }


}
