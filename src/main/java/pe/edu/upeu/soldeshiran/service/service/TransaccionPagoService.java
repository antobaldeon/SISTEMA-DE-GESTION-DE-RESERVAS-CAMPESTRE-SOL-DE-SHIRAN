package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface TransaccionPagoService {
    TransaccionPagoResponseDTO crear(TransaccionPagoRequestDTO r);

    TransaccionPagoResponseDTO actualizar(Long id, TransaccionPagoRequestDTO r);

    TransaccionPagoResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<TransaccionPagoResponseDTO> listar();
}
