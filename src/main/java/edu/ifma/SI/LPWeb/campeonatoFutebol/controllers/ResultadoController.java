package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;


import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Resultado;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.ResultadoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {
    private final ResultadoService service;

    public ResultadoController(ResultadoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resultado> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Resultado buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @PostMapping
    public Resultado salvar(@RequestBody Resultado resultado) {
        return service.salvar(resultado);
    }

    @PutMapping("/{id}")
    public Resultado atualizar(@PathVariable Integer id, @RequestBody Resultado resultado) {
        resultado.setId(id);
        return service.atualizar(resultado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }
}
