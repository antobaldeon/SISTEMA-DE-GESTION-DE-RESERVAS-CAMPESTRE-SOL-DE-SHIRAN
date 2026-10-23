package pe.edu.upeu.soldeshiran.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.service.service.CabanaService;

@RestController
@org.springframework.validation.annotation.Validated
@RequestMapping("/api/v1/cabanas")
public class CabanaController {
    private final CabanaService service;

    public CabanaController(CabanaService s) {
        service = s;
    }

    @GetMapping
    public ResponseEntity<List<CabanaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabanaResponseDTO> buscar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CabanaResponseDTO> crear(@Valid @RequestBody CabanaRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabanaResponseDTO> actualizar(@PathVariable @jakarta.validation.constraints.Positive Long id, @Valid @RequestBody CabanaRequestDTO r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}