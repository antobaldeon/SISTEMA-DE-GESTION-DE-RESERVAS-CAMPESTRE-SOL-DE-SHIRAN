package pe.edu.upeu.soldeshiran.controller;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.service.service.WebhookLogService;

@RestController
@org.springframework.validation.annotation.Validated
@RequestMapping("/api/v1/webhook-logs")
public class WebhookLogController {
    private final WebhookLogService service;

    public WebhookLogController(WebhookLogService s) {
        service = s;
    }

    @GetMapping
    public ResponseEntity<List<WebhookLogResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebhookLogResponseDTO> buscar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<WebhookLogResponseDTO> crear(@Valid @RequestBody WebhookLogRequestDTO r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WebhookLogResponseDTO> actualizar(@PathVariable @jakarta.validation.constraints.Positive Long id, @Valid @RequestBody WebhookLogRequestDTO r) {
        return ResponseEntity.ok(service.actualizar(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @jakarta.validation.constraints.Positive Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}