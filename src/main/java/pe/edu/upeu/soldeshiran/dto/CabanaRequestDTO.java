package pe.edu.upeu.soldeshiran.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CabanaRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String nombre;
    @NotBlank
    private String descripcion;
    @NotNull
    @Positive
    @Digits(integer = 10, fraction = 0)
    private Long aforoMaximo;
    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 0)
    private Long numHabitaciones;
    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 0)
    private Long numBanos;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioBaseNoche;
    @Size(max = 20)
    private String estado;
    @NotBlank
    @Size(max = 255)
    private String imagenPrincipal;
}
