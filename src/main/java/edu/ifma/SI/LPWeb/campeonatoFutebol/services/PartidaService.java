package edu.ifma.SI.LPWeb.campeonatoFutebol.services;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {
    private final PartidaRepository repository;

    public PartidaService(PartidaRepository repository) {
        this.repository = repository;
    }

    public List<Partida> listarTodos() {
        return repository.findAll();
    }

    public Optional<Partida> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public List<Partida> partidasPassadas() {
        return repository.findByDataBefore(LocalDate.now());
    }

    public List<Partida> partidasFuturas() {
        return repository.findByDataAfter(LocalDate.now());
    }

    public List<Partida> partidasPorCampeonato(Integer campeonatoId) {
        return repository.findByCampeonatoId(campeonatoId);
    }

    public Partida salvar(Partida partida) {
        return repository.save(partida);
    }

    public Partida atualizar(Partida partida) {
        return repository.save(partida);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}