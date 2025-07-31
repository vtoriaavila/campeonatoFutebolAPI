package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.PartidaDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.PartidaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService service;

    public PartidaController(PartidaService service) {
        this.service = service;
    }

    @GetMapping
    public List<PartidaDTO> listarTodos() {
        return service.listarTodos();  // O serviço já retorna os DTOs
    }

    @GetMapping("/{id}")
    public PartidaDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);  // O serviço já retorna o DTO
    }

    @GetMapping("/passadas")
    public List<PartidaDTO> partidasPassadas() {
        return service.partidasPassadas();  // O serviço já retorna os DTOs
    }

    @GetMapping("/futuras")
    public List<PartidaDTO> partidasFuturas() {
        return service.partidasFuturas();  // O serviço já retorna os DTOs
    }

    @GetMapping("/campeonato/{id}")
    public List<PartidaDTO> partidasPorCampeonato(@PathVariable Integer id) {
        return service.partidasPorCampeonato(id);  // O serviço já retorna os DTOs
    }

    @PostMapping
    public PartidaDTO salvar(@RequestBody PartidaDTO partidaDTO) {
        return service.salvar(partidaDTO);  // O serviço converte o DTO para entidade e salva
    }

    @PutMapping("/{id}")
    public PartidaDTO atualizar(@PathVariable Integer id, @RequestBody PartidaDTO partidaDTO) {
        return service.atualizar(id, partidaDTO);  // O serviço converte o DTO para entidade e atualiza
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);  // Deleta diretamente no serviço
    }
}
