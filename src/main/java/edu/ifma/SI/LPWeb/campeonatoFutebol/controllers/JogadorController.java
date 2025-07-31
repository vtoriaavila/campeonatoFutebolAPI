package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.JogadorDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.IdadeInvalidaException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.JogadorNaoEncontradoException;
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

    // Listar todos os jogadores
    @GetMapping
    public ResponseEntity<List<JogadorDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Listar jogadores com paginação
    @GetMapping("/paginado")
    public ResponseEntity<Page<JogadorDTO>> listarPaginado(Pageable pageable) {
        return ResponseEntity.ok(service.listarPaginado(pageable));
    }

    // Buscar jogador por ID
    @GetMapping("/{id}")
    public ResponseEntity<JogadorDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    // Salvar novo jogador
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody JogadorDTO jogadorDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(jogadorDTO));
        } catch (IdadeInvalidaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Atualizar jogador existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody JogadorDTO jogadorDTO) {
        try {
            return ResponseEntity.ok(service.atualizar(id, jogadorDTO));
        } catch (JogadorNaoEncontradoException | IdadeInvalidaException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    // Deletar jogador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Trata exceções de idade inválida
    @ExceptionHandler(IdadeInvalidaException.class)
    public ResponseEntity<String> handleIdadeInvalida(IdadeInvalidaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Trata exceções de jogador não encontrado
    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public ResponseEntity<String> handleJogadorNaoEncontrado(JogadorNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
