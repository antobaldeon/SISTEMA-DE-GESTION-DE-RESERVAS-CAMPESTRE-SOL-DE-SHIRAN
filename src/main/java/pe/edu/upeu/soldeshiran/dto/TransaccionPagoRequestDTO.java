package pe.edu.upeu.soldeshiran.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TransaccionPagoRequestDTO {
    @NotNull
    @Positive
    @Digits(integer = 19, fraction = 0)
    private Long idReserva;
    @Size(max = 100)
    private String idExternoPasarela;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal monto;
    @Size(max = 10)
    private String moneda;
    @Size(max = 50)
    private String metodoPago;
    @Size(max = 30)
    private String estadoTransaccion;
}
