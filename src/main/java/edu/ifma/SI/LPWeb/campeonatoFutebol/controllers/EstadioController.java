package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.EstadioDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Estadio;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.EstadioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

    private final EstadioService service;

    public EstadioController(EstadioService service) {
        this.service = service;
    }

    @GetMapping
    public List<EstadioDTO> listarTodos() {
        return service.listarTodos();  // O serviço já retorna os DTOs
    }

    @GetMapping("/{id}")
    public EstadioDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);  // O serviço já retorna o DTO
    }

    @PostMapping
    public Estadio salvar(@RequestBody EstadioDTO estadioDTO) {
        return service.salvar(estadioDTO);  // O serviço converte o DTO para entidade e salva
    }

    @PutMapping("/{id}")
    public Estadio atualizar(@PathVariable Integer id, @RequestBody EstadioDTO estadioDTO) {
        return service.atualizar(id, estadioDTO);  // O serviço converte o DTO para entidade e atualiza
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);  // Deleta diretamente no serviço
    }
}
