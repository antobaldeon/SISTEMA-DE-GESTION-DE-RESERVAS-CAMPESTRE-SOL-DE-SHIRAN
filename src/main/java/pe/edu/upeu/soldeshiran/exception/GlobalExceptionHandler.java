package pe.edu.upeu.soldeshiran.exception;

import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, Object> body(HttpStatus s, String m, HttpServletRequest r) {
        return Map.of("timestamp", LocalDateTime.now(), "status", s.value(), "error", s.getReasonPhrase(), "message", m, "path", r.getRequestURI());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    ResponseEntity<?> nf(Exception e, HttpServletRequest r) {
        return ResponseEntity.status(404).body(body(HttpStatus.NOT_FOUND, e.getMessage(), r));
    }

    @ExceptionHandler(ReglaNegocioException.class)
    ResponseEntity<?> cf(Exception e, HttpServletRequest r) {
        return ResponseEntity.status(409).body(body(HttpStatus.CONFLICT, e.getMessage(), r));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<?> iv(Exception e, HttpServletRequest r) {
        return ResponseEntity.status(409).body(body(HttpStatus.CONFLICT, "La operación viola una restricción de integridad", r));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> bad(MethodArgumentNotValidException e, HttpServletRequest r) {
        Map<String, String> f = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors().forEach(x -> f.put(x.getField(), x.getDefaultMessage()));
        return ResponseEntity.badRequest().body(Map.of("timestamp", LocalDateTime.now(), "status", 400, "error", "Bad Request", "message", "Errores de validación", "path", r.getRequestURI(), "details", f));
    }
}