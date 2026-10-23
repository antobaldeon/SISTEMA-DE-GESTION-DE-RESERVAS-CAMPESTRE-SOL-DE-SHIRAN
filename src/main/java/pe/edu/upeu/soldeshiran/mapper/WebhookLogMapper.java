package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class WebhookLogMapper {
    public WebhookLogResponseDTO toResponse(WebhookLog e) {
        return new WebhookLogResponseDTO(e.getId(), (e.getTransaccion() == null ? null : e.getTransaccion().getId()), e.getIdTransaccionPasarela(), e.getPayloadJson(), e.getEstadoProcesamiento(), e.getFechaRecepcion());
    }
}
