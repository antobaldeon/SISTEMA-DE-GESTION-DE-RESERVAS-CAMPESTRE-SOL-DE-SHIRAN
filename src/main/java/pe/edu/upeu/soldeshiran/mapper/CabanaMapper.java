package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class CabanaMapper {
    public CabanaResponseDTO toResponse(Cabana e) {
        return new CabanaResponseDTO(e.getId(), e.getNombre(), e.getDescripcion(), e.getAforoMaximo(), e.getNumHabitaciones(), e.getNumBanos(), e.getPrecioBaseNoche(), e.getEstado(), e.getImagenPrincipal(), e.getFechaCreacion());
    }
}
