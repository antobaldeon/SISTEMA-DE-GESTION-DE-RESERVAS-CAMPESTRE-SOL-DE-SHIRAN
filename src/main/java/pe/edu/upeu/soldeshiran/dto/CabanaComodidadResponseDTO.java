package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabanaComodidadResponseDTO {
    private Long idComodidad;
    private Long idCabana;
    private String nombreComodidad;
}
