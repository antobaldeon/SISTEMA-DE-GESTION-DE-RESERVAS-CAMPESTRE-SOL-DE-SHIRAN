package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class CabanaImagenMapper {
    public CabanaImagenResponseDTO toResponse(CabanaImagen e) {
        return new CabanaImagenResponseDTO(e.getId(), e.getCabana().getId(), e.getUrlImagen());
    }
}
