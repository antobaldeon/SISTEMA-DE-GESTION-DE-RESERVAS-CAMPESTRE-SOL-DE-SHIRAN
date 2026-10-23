package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface ReservaService {
    ReservaResponseDTO crear(ReservaRequestDTO r);

    ReservaResponseDTO actualizar(Long id, ReservaRequestDTO r);

    ReservaResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<ReservaResponseDTO> listar();
}
