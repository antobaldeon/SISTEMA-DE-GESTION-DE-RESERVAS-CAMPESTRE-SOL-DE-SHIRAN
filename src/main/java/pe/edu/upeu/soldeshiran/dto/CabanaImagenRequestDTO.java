package pe.edu.upeu.soldeshiran.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CabanaImagenRequestDTO {
    @NotNull
    @Positive
    @Digits(integer = 19, fraction = 0)
    private Long idCabana;
    @NotBlank
    @Size(max = 255)
    private String urlImagen;
}
