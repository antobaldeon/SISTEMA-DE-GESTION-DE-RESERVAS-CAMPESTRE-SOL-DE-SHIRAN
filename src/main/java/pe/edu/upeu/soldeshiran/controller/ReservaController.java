package pe.edu.upeu.soldeshiran.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.service.service.ReservaService;

@RestController
@org.springframework.validation.annotation.Validated
@RequestMapping("/api/v1/reservas")
public class ReservaController {
    private final ReservaService service;

    public ReservaController(ReservaService s) {
        service = s;
    }

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crear(@Valid @RequestBody ReservaRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> actualizar(@PathVariable @jakarta.validation.constraints.Positive Long id, @Valid @RequestBody ReservaRequestDTO r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}