package pe.edu.upeu.soldeshiran.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.service.service.TransaccionPagoService;

@RestController
@org.springframework.validation.annotation.Validated
@RequestMapping("/api/v1/transacciones-pago")
public class TransaccionPagoController {
    private final TransaccionPagoService service;

    public TransaccionPagoController(TransaccionPagoService s) {
        service = s;
    }

    @GetMapping
    public ResponseEntity<List<TransaccionPagoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionPagoResponseDTO> buscar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<TransaccionPagoResponseDTO> crear(@Valid @RequestBody TransaccionPagoRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransaccionPagoResponseDTO> actualizar(@PathVariable @jakarta.validation.constraints.Positive Long id, @Valid @RequestBody TransaccionPagoRequestDTO r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}