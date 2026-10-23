package pe.edu.upeu.soldeshiran.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class WebhookLogRequestDTO {
    @Positive
    @Digits(integer = 19, fraction = 0)
    private Long idTransaccion;
    @Size(max = 100)
    private String idTransaccionPasarela;
    @NotBlank
    private String payloadJson;
    @Size(max = 30)
    private String estadoProcesamiento;
}
