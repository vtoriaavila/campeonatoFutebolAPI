package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.PartidaDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.PartidaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidaService {

    private final PartidaRepository repository;
    private final ModelMapper modelMapper;

    public PartidaService(PartidaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    // Listar todas as partidas convertendo para DTO
    public List<PartidaDTO> listarTodos() {
        return repository.findAll().stream()
                .map(partida -> modelMapper.map(partida, PartidaDTO.class))  // Convertendo Partida para PartidaDTO
                .collect(Collectors.toList());
    }

    // Buscar partida por ID e retornar o DTO
    public PartidaDTO buscarPorId(Integer id) {
        Partida partida = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida com ID " + id + " não encontrada."));
        return modelMapper.map(partida, PartidaDTO.class);  // Convertendo Partida para PartidaDTO
    }

    // Listar partidas passadas
    public List<PartidaDTO> partidasPassadas() {
        return repository.findByDataBefore(LocalDate.now()).stream()
                .map(partida -> modelMapper.map(partida, PartidaDTO.class))  // Convertendo Partida para PartidaDTO
                .collect(Collectors.toList());
    }

    // Listar partidas futuras
    public List<PartidaDTO> partidasFuturas() {
        return repository.findByDataAfter(LocalDate.now()).stream()
                .map(partida -> modelMapper.map(partida, PartidaDTO.class))  // Convertendo Partida para PartidaDTO
                .collect(Collectors.toList());
    }

    // Listar partidas por campeonato
    public List<PartidaDTO> partidasPorCampeonato(Integer campeonatoId) {
        return repository.findByCampeonatoId(campeonatoId).stream()
                .map(partida -> modelMapper.map(partida, PartidaDTO.class))  // Convertendo Partida para PartidaDTO
                .collect(Collectors.toList());
    }

    // Salvar partida
    public PartidaDTO salvar(PartidaDTO partidaDTO) {
        Partida partida = modelMapper.map(partidaDTO, Partida.class);  // Convertendo PartidaDTO para Partida
        Partida partidaSalva = repository.save(partida);
        return modelMapper.map(partidaSalva, PartidaDTO.class);  // Convertendo a entidade salva de volta para DTO
    }

    // Atualizar partida
    public PartidaDTO atualizar(Integer id, PartidaDTO partidaDTO) {
        Partida partidaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida com ID " + id + " não encontrada para atualização."));
        Partida partida = modelMapper.map(partidaDTO, Partida.class);  // Convertendo PartidaDTO para Partida
        partida.setId(id);  // Atribuindo o ID para atualização
        Partida partidaAtualizada = repository.save(partida);
        return modelMapper.map(partidaAtualizada, PartidaDTO.class);  // Convertendo a entidade atualizada para DTO
    }

    // Deletar partida
    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}
