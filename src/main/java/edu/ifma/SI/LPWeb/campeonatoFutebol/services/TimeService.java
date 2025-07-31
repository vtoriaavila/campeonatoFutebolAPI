package edu.ifma.SI.LPWeb.campeonatoFutebol.services;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.TimeDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.TimeNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import edu.ifma.SI.LPWeb.campeonatoFutebol.repository.TimeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {
    private final TimeRepository repository;
    private final ModelMapper modelMapper;

    public TimeService(TimeRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<TimeDTO> listarTodos() {
        return repository.findAll().stream()
                .map(time -> modelMapper.map(time, TimeDTO.class))
                .toList();
    }

    public TimeDTO buscarPorId(Integer id) {
        Time time = repository.findById(id)
                .orElseThrow(() -> new TimeNaoEncontradoException("Time com ID " + id + " não encontrado."));
        return modelMapper.map(time, TimeDTO.class);
    }

    public Time salvar(TimeDTO timeDTO) {
        Time time = modelMapper.map(timeDTO, Time.class);
        return repository.save(time);
    }

    public Time atualizar(Integer id, TimeDTO timeDTO) {
        if (!repository.existsById(id)) {
            throw new TimeNaoEncontradoException("Time com ID " + id + " não encontrado para atualização.");
        }

        Time time = modelMapper.map(timeDTO, Time.class);
        time.setId(id);
        return repository.save(time);
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new TimeNaoEncontradoException("Time com ID " + id + " não encontrado para exclusão.");
        }

        repository.deleteById(id);
    }
}
