package edu.ifma.SI.LPWeb.campeonatoFutebol.controllers;

import edu.ifma.SI.LPWeb.campeonatoFutebol.DTO.TimeDTO;
import edu.ifma.SI.LPWeb.campeonatoFutebol.exception.TimeNaoEncontradoException;
import edu.ifma.SI.LPWeb.campeonatoFutebol.services.TimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    private final TimeService service;

    public TimeController(TimeService service) {
        this.service = service;
    }

    // Listar todos os times
    @GetMapping
    public ResponseEntity<List<TimeDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Buscar time por ID
    @GetMapping("/{id}")
    public ResponseEntity<TimeDTO> buscarPorId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (TimeNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Salvar novo time
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody TimeDTO timeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(timeDTO));
    }

    // Atualizar time existente
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Integer id, @RequestBody TimeDTO timeDTO) {
        try {
            return ResponseEntity.ok(service.atualizar(id, timeDTO));
        } catch (TimeNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // Deletar time
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (TimeNaoEncontradoException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Handler opcional para exceções personalizadas
    @ExceptionHandler(TimeNaoEncontradoException.class)
    public ResponseEntity<String> handleTimeNaoEncontrado(TimeNaoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
