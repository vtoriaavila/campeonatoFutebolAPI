package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;


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
    public List<Estadio> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Estadio buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public Estadio salvar(@RequestBody Estadio estadio) {
        return service.salvar(estadio);
    }

    @PutMapping("/{id}")
    public Estadio atualizar(@PathVariable Integer id, @RequestBody Estadio estadio) {
        estadio.setId(id);
        return service.atualizar(estadio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
