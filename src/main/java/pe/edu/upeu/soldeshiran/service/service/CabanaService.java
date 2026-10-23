package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface CabanaService {
    CabanaResponseDTO crear(CabanaRequestDTO r);

    CabanaResponseDTO actualizar(Long id, CabanaRequestDTO r);

    CabanaResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<CabanaResponseDTO> listar();
}
