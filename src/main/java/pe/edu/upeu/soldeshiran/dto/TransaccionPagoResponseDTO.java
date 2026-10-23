package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionPagoResponseDTO {
    private Long idTransaccion;
    private Long idReserva;
    private String idExternoPasarela;
    private BigDecimal monto;
    private String moneda;
    private String metodoPago;
    private String estadoTransaccion;
    private LocalDateTime fechaTransaccion;
}
