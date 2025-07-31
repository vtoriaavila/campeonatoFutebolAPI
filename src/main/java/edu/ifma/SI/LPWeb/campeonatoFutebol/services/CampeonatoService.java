package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.CampeonatoDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.CampeonatoNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.CampeonatoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService {
    private final CampeonatoRepository repository;
    private final ModelMapper modelMapper;

    public CampeonatoService(CampeonatoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    // Lista todos os campeonatos convertidos para DTO
    public List<CampeonatoDTO> listarTodos() {
        return repository.findAll().stream()
                .map(campeonato -> modelMapper.map(campeonato, CampeonatoDTO.class))  // Convertendo Campeonato para CampeonatoDTO
                .toList();
    }

    // Buscar campeonato por ID e retornar DTO
    public CampeonatoDTO buscarPorId(Integer id) {
        Campeonato campeonato = repository.findById(id)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato com ID " + id + " não encontrado."));
        return modelMapper.map(campeonato, CampeonatoDTO.class);  // Convertendo Campeonato para CampeonatoDTO
    }

    // Salvar um novo campeonato a partir de um DTO
    public Campeonato salvar(CampeonatoDTO campeonatoDTO) {
        Campeonato campeonato = modelMapper.map(campeonatoDTO, Campeonato.class);  // Convertendo CampeonatoDTO para Campeonato
        return repository.save(campeonato);  // Salvando no banco de dados
    }

    // Atualizar um campeonato existente
    public Campeonato atualizar(Integer id, CampeonatoDTO campeonatoDTO) {
        // Verificar se o campeonato existe antes de atualizar
        Campeonato campeonatoExistente = repository.findById(id)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException("Campeonato com ID " + id + " não encontrado para atualização."));

        // Mapear os dados do DTO para a entidade
        Campeonato campeonato = modelMapper.map(campeonatoDTO, Campeonato.class);
        campeonato.setId(id);

        return repository.save(campeonato);
    }

    // Deletar um campeonato
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new CampeonatoNaoEncontradoException("Campeonato com ID " + id + " não encontrado para exclusão.");
        }
        repository.deleteById(id);  // Deletando o campeonato
    }
}
