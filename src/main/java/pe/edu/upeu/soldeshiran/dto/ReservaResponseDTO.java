package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {
    private Long idReserva;
    private Long idCabana;
    private Long idUsuario;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Long cantidadHuespedes;
    private BigDecimal precioNocheAplicado;
    private BigDecimal montoTotal;
    private BigDecimal montoAdelanto;
    private String estadoReserva;
    private LocalDateTime expiraEn;
    private LocalDateTime fechaCreacion;
}
