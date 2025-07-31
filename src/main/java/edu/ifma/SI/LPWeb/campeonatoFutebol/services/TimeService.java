package edu.ifma.SI.LPWeb.campeonatoFutebol.services;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.TimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {
    private final TimeRepository repository;

    public TimeService(TimeRepository repository) {
        this.repository = repository;
    }

    public List<Time> listarTodos() {
        return repository.findAll();
    }

    public Optional<Time> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Time salvar(Time time) {
        return repository.save(time);
    }

    public Time atualizar(Time time) {
        return repository.save(time);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}