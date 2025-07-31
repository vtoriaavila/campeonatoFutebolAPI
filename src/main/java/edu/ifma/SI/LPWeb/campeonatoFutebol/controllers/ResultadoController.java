package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.ResultadoDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.ResultadoNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.ResultadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

    private final ResultadoService service;

    public ResultadoController(ResultadoService service) {
        this.service = service;
    }

    // Listar todos os resultados
    @GetMapping
    public ResponseEntity<List<ResultadoDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar resultado por ID
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (ResultadoNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Salvar novo resultado
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ResultadoDTO resultadoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(resultadoDTO));
    }

    // Atualizar resultado existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody ResultadoDTO resultadoDTO) {
        try {
            return ResponseEntity.ok(service.atualizar(id, resultadoDTO));
        } catch (ResultadoNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Deletar resultado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (ResultadoNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Handler global opcional
    @ExceptionHandler(ResultadoNaoEncontradoException.class)
    public ResponseEntity<String> handleResultadoNaoEncontrado(ResultadoNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
