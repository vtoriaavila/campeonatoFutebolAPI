package edu.ifma.SI.LPWeb.campeonatoFutebol.services;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Estadio;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.EstadioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadioService {
    private final EstadioRepository repository;

    public EstadioService(EstadioRepository repository) {
        this.repository = repository;
    }

    public List<Estadio> listarTodos() {
        return repository.findAll();
    }

    public Optional<Estadio> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Estadio salvar(Estadio estadio) {
        return repository.save(estadio);
    }

    public Estadio atualizar(Estadio estadio) {
        return repository.save(estadio);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}