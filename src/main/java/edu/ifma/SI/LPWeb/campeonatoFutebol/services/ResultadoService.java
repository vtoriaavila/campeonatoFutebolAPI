package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.ResultadoDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.ResultadoNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.ResultadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService {
    private final ResultadoRepository repository;
    private final ModelMapper modelMapper;

    public ResultadoService(ResultadoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<ResultadoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(resultado -> modelMapper.map(resultado, ResultadoDTO.class))
                .toList();
    }

    public ResultadoDTO buscarPorId(Integer id) {
        Resultado resultado = repository.findById(id)
                .orElseThrow(() -> new ResultadoNaoEncontradoException("Resultado com ID " + id + " não encontrado."));
        return modelMapper.map(resultado, ResultadoDTO.class);
    }

    public Resultado salvar(ResultadoDTO resultadoDTO) {
        Resultado resultado = modelMapper.map(resultadoDTO, Resultado.class);
        return repository.save(resultado);
    }

    public Resultado atualizar(Integer id, ResultadoDTO resultadoDTO) {
        if (!repository.existsById(id)) {
            throw new ResultadoNaoEncontradoException("Resultado com ID " + id + " não encontrado para atualização.");
        }

        Resultado resultado = modelMapper.map(resultadoDTO, Resultado.class);
        resultado.setId(id);

        return repository.save(resultado);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResultadoNaoEncontradoException("Resultado com ID " + id + " não encontrado para exclusão.");
        }

        repository.deleteById(id);
    }
}
