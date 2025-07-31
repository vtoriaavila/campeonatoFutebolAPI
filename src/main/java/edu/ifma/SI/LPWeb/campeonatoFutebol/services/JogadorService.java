package edu.ifma.SI.LPWeb.campeonatoFutebol.services;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Jogador;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.JogadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {
    private final JogadorRepository repository;

    public JogadorService(JogadorRepository repository) {
        this.repository = repository;
    }

    public List<Jogador> listarTodos() {
        return repository.findAll();
    }

    public Optional<Jogador> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Jogador salvar(Jogador jogador) {
        return repository.save(jogador);
    }

    public Jogador atualizar(Jogador jogador) {
        return repository.save(jogador);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public Page<Jogador> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }
}

