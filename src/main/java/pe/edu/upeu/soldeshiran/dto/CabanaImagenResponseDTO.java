package pe.edu.upeu.soldeshiran.dto;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CabanaImagenResponseDTO {
    private Long idImagen;
    private Long idCabana;
    private String urlImagen;
}
