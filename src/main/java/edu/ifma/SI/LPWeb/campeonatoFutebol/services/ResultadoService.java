package edu.ifma.SI.LPWeb.campeonatoFutebol.services;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultadoService {
    private final ResultadoRepository repository;

    public ResultadoService(ResultadoRepository repository) {
        this.repository = repository;
    }

    public List<Resultado> listarTodos() {
        return repository.findAll();
    }

    public Optional<Resultado> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public Resultado salvar(Resultado resultado) {
        return repository.save(resultado);
    }

    public Resultado atualizar(Resultado resultado) {
        return repository.save(resultado);
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}

