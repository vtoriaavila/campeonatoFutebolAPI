package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.EstadioDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Estadio;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.EstadioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadioService {

    private final EstadioRepository repository;
    private final ModelMapper modelMapper;

    public EstadioService(EstadioRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    // Listar todos os estádios convertendo para DTO
    public List<EstadioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(estadio -> modelMapper.map(estadio, EstadioDTO.class))  // Convertendo Estádio para EstádioDTO
                .collect(Collectors.toList());
    }

    // Buscar estádio por ID e retornar o DTO
    public EstadioDTO buscarPorId(Integer id) {
        Estadio estadio = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estádio com ID " + id + " não encontrado."));
        return modelMapper.map(estadio, EstadioDTO.class);  // Convertendo Estádio para EstádioDTO
    }

    // Salvar estádio convertendo o DTO para entidade
    public Estadio salvar(EstadioDTO estadioDTO) {
        Estadio estadio = modelMapper.map(estadioDTO, Estadio.class);  // Convertendo EstádioDTO para Estádio
        return repository.save(estadio);
    }

    // Atualizar estádio
    public Estadio atualizar(Integer id, EstadioDTO estadioDTO) {
        Estadio estadioExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estádio com ID " + id + " não encontrado para atualização."));
        Estadio estadio = modelMapper.map(estadioDTO, Estadio.class);
        estadio.setId(id);
        return repository.save(estadio);
    }

    // Deletar estádio
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Estádio com ID " + id + " não encontrado para exclusão.");
        }
        repository.deleteById(id);
    }
}
