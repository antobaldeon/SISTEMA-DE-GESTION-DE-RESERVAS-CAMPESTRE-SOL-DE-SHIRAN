package pe.edu.upeu.soldeshiran.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ReservaRequestDTO {
    @NotNull
    @Positive
    @Digits(integer = 19, fraction = 0)
    private Long idCabana;
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 0)
    private Long idUsuario;
    @NotNull
    private LocalDate fechaIngreso;
    @NotNull
    private LocalDate fechaSalida;
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 0)
    private Long cantidadHuespedes;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioNocheAplicado;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal montoTotal;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal montoAdelanto;
    @Size(max = 30)
    private String estadoReserva;
    @NotNull
    private LocalDateTime expiraEn;

    @AssertTrue(message = "La fecha de salida debe ser posterior al ingreso")
    public boolean isRangoFechaValido() {
        return fechaIngreso == null || fechaSalida == null || fechaSalida.isAfter(fechaIngreso);
    }
}
