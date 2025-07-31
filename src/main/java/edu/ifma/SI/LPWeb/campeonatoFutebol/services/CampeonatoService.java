package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService {
    private final CampeonatoRepository repository;

    public CampeonatoService(CampeonatoRepository repository) {
        this.repository = repository;
    }

    public List<Campeonato> listarTodos() {
        return repository.findAll();
    }

    public Optional<Campeonato> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Campeonato salvar(Campeonato campeonato) {
        return repository.save(campeonato);
    }

    public Campeonato atualizar(Campeonato campeonato) {
        return repository.save(campeonato);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
