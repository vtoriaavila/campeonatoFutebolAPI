package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.IdadeInvalidaException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.model.Jogador;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.JogadorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    private final JogadorService service;

    public JogadorController(JogadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jogador> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/paginado")
    public Page<Jogador> listarPaginado(Pageable pageable) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/{id}")
    public Jogador buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElse(null);
    }

    @PostMapping
    public Jogador salvar(@RequestBody Jogador jogador) {
        return service.salvar(jogador);
    }

    @PutMapping("/{id}")
    public Jogador atualizar(@PathVariable Integer id, @RequestBody Jogador jogador) {
        jogador.setId(id);
        return service.atualizar(jogador);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

    @ExceptionHandler(IdadeInvalidaException.class)
    public ResponseEntity<String> handleIdadeInvalida(IdadeInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
