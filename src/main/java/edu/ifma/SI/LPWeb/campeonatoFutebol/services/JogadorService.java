package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.JogadorDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.IdadeInvalidaException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.JogadorNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Jogador;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.JogadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class JogadorService {
    private final JogadorRepository repository;
    private final ModelMapper modelMapper;

    public JogadorService(JogadorRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Jogador salvar(JogadorDTO jogadorDTO) {
        Jogador jogador = modelMapper.map(jogadorDTO, Jogador.class);

        if (!isIdadeValida(jogador.getNascimento())) {
            throw new IdadeInvalidaException("Jogador deve ter pelo menos 18 anos.");
        }

        return repository.save(jogador);
    }

    public Jogador atualizar(Integer id, JogadorDTO jogadorDTO) {
        if (!repository.existsById(id)) {
            throw new JogadorNaoEncontradoException("Jogador com ID " + id + " não encontrado para atualização.");
        }

        Jogador jogador = modelMapper.map(jogadorDTO, Jogador.class);
        jogador.setId(id);

        if (!isIdadeValida(jogador.getNascimento())) {
            throw new IdadeInvalidaException("Jogador deve ter pelo menos 18 anos.");
        }

        return repository.save(jogador);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new JogadorNaoEncontradoException("Jogador com ID " + id + " não encontrado para exclusão.");
        }

        repository.deleteById(id);
    }

    public List<JogadorDTO> listarTodos() {
        return repository.findAll().stream()
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class))
                .toList();
    }

    public Page<JogadorDTO> listarPaginado(Pageable pageable) {
        return repository.findAll(pageable)
                .map(jogador -> modelMapper.map(jogador, JogadorDTO.class));
    }

    public JogadorDTO buscarPorId(Integer id) {
        Jogador jogador = repository.findById(id)
                .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador com ID " + id + " não encontrado."));
        return modelMapper.map(jogador, JogadorDTO.class);
    }

    private boolean isIdadeValida(LocalDate nascimento) {
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(nascimento, hoje).getYears();
        return idade >= 18;
    }
}
