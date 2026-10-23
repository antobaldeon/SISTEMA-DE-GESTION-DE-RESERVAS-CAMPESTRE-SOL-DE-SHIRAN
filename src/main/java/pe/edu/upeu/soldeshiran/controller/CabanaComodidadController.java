package pe.edu.upeu.soldeshiran.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.service.service.CabanaComodidadService;

@RestController
@org.springframework.validation.annotation.Validated
@RequestMapping("/api/v1/cabana-comodidades")
public class CabanaComodidadController {
    private final CabanaComodidadService service;

    public CabanaComodidadController(CabanaComodidadService s) {
        service = s;
    }

    @GetMapping
    public ResponseEntity<List<CabanaComodidadResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabanaComodidadResponseDTO> buscar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CabanaComodidadResponseDTO> crear(@Valid @RequestBody CabanaComodidadRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabanaComodidadResponseDTO> actualizar(@PathVariable @jakarta.validation.constraints.Positive Long id, @Valid @RequestBody CabanaComodidadRequestDTO r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}