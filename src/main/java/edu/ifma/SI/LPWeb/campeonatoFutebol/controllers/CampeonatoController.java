package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Campeonato;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Partida;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.CampeonatoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
    private final CampeonatoService service;

    public CampeonatoController(CampeonatoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Campeonato> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Campeonato buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public Campeonato salvar(@RequestBody Campeonato campeonato) {
        return service.salvar(campeonato);
    }

    @PutMapping("/{id}")
    public Campeonato atualizar(@PathVariable Integer id, @RequestBody Campeonato campeonato) {
        campeonato.setId(id);
        return service.atualizar(campeonato);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @GetMapping("/{id}/times")
    public Set<Time> listarTimesDoCampeonato(@PathVariable Integer id) {
        return service.listarTimesDoCampeonato(id);
    }

    @GetMapping("/{id}/partidas/passadas")
    public List<Partida> listarPartidasPassadas(@PathVariable Integer id) {
        return service.listarPartidasPassadas(id);
    }

    @GetMapping("/{id}/partidas/futuras")
    public List<Partida> listarPartidasFuturas(@PathVariable Integer id) {
        return service.listarPartidasFuturas(id);
    }


}
