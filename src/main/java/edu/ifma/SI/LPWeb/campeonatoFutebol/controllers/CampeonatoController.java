package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.CampeonatoDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.CampeonatoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {

    private final CampeonatoService service;

    public CampeonatoController(CampeonatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CampeonatoDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public CampeonatoDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Campeonato salvar(@RequestBody CampeonatoDTO campeonatoDTO) {
        return service.salvar(campeonatoDTO);
    }

    @PutMapping("/{id}")
    public Campeonato atualizar(@PathVariable Integer id, @RequestBody CampeonatoDTO campeonatoDTO) {
        return service.atualizar(id, campeonatoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
