package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebhookLogResponseDTO {
    private Long idLog;
    private Long idTransaccion;
    private String idTransaccionPasarela;
    private String payloadJson;
    private String estadoProcesamiento;
    private LocalDateTime fechaRecepcion;
}
