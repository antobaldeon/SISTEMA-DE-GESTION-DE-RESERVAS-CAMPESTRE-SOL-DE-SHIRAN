package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface CabanaImagenService {
    CabanaImagenResponseDTO crear(CabanaImagenRequestDTO r);

    CabanaImagenResponseDTO actualizar(Long id, CabanaImagenRequestDTO r);

    CabanaImagenResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<CabanaImagenResponseDTO> listar();
}
