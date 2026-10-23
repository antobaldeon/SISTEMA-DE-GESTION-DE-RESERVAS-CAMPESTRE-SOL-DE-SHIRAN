package pe.edu.upeu.soldeshiran.mapper;

import org.springframework.stereotype.Component;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.dto.*;

@Component
public class TransaccionPagoMapper {
    public TransaccionPagoResponseDTO toResponse(TransaccionPago e) {
        return new TransaccionPagoResponseDTO(e.getId(), e.getReserva().getId(), e.getIdExternoPasarela(), e.getMonto(), e.getMoneda(), e.getMetodoPago(), e.getEstadoTransaccion(), e.getFechaTransaccion());
    }
}
