package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
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
    public List<Partida> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Partida buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @GetMapping("/passadas")
    public List<Partida> partidasPassadas() {
        return service.partidasPassadas();
    }

    @GetMapping("/futuras")
    public List<Partida> partidasFuturas() {
        return service.partidasFuturas();
    }

    @GetMapping("/campeonato/{id}")
    public List<Partida> partidasPorCampeonato(@PathVariable Integer id) {
        return service.partidasPorCampeonato(id);
    }

    @PostMapping
    public Partida salvar(@RequestBody Partida partida) {
        return service.salvar(partida);
    }

    @PutMapping("/{id}")
    public Partida atualizar(@PathVariable Integer id, @RequestBody Partida partida) {
        partida.setId(id);
        return service.atualizar(partida);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
