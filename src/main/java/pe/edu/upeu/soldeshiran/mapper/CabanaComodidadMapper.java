package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class CabanaComodidadMapper {
    public CabanaComodidadResponseDTO toResponse(CabanaComodidad e) {
        return new CabanaComodidadResponseDTO(e.getId(), e.getCabana().getId(), e.getNombreComodidad());
    }
}
