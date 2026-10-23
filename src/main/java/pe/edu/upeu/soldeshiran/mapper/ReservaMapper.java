package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class ReservaMapper {
    public ReservaResponseDTO toResponse(Reserva e) {
        return new ReservaResponseDTO(e.getId(), e.getCabana().getId(), e.getIdUsuario(), e.getFechaIngreso(), e.getFechaSalida(), e.getCantidadHuespedes(), e.getPrecioNocheAplicado(), e.getMontoTotal(), e.getMontoAdelanto(), e.getEstadoReserva(), e.getExpiraEn(), e.getFechaCreacion());
    }
}
