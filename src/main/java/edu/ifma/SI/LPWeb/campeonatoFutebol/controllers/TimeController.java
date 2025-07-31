package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Time;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.TimeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {
    private final TimeService service;

    public TimeController(TimeService service) {
        this.service = service;
    }

    @GetMapping
    public List<Time> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Time buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public Time salvar(@RequestBody Time time) {
        return service.salvar(time);
    }

    @PutMapping("/{id}")
    public Time atualizar(@PathVariable Integer id, @RequestBody Time time) {
        time.setId(id);
        return service.atualizar(time);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
