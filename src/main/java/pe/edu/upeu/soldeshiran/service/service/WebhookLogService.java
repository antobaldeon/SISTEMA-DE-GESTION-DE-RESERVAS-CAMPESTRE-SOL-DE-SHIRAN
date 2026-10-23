package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface WebhookLogService {
    WebhookLogResponseDTO crear(WebhookLogRequestDTO r);

    WebhookLogResponseDTO actualizar(Long id, WebhookLogRequestDTO r);

    WebhookLogResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<WebhookLogResponseDTO> listar();
}
