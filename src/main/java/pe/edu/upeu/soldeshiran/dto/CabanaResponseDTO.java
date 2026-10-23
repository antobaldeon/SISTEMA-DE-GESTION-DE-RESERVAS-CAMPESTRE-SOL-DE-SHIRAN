package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabanaResponseDTO {
    private Long idCabana;
    private String nombre;
    private String descripcion;
    private Long aforoMaximo;
    private Long numHabitaciones;
    private Long numBanos;
    private BigDecimal precioBaseNoche;
    private String estado;
    private String imagenPrincipal;
    private LocalDateTime fechaCreacion;
}
