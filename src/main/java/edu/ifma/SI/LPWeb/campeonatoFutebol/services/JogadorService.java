package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.IdadeInvalidaException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Jogador;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.JogadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {
    private final JogadorRepository repository;

    public JogadorService(JogadorRepository repository) {
        this.repository = repository;
    }

    public Jogador salvar(Jogador jogador) {
        if (!isIdadeValida(jogador.getNascimento())) {
            throw new IdadeInvalidaException("Jogador deve ter pelo menos 18 anos.");
        }
        return repository.save(jogador);
    }

    public Jogador atualizar(Jogador jogador) {
        if (!isIdadeValida(jogador.getNascimento())) {
            throw new IdadeInvalidaException("Jogador deve ter pelo menos 18 anos.");
        }
        return repository.save(jogador);
    }


    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public List<Jogador> listarTodos() {
        return repository.findAll();
    }

    public Page<Jogador> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Jogador> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    private boolean isIdadeValida(LocalDate nascimento) {
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(nascimento, hoje).getYears();
        return idade >= 18;
    }
}
