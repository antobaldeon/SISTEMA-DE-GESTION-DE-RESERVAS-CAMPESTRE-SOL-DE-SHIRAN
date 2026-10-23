package pe.edu.upeu.soldeshiran.service.service;

import java.util.*;

import pe.edu.upeu.soldeshiran.dto.*;

public interface CabanaComodidadService {
    CabanaComodidadResponseDTO crear(CabanaComodidadRequestDTO r);

    CabanaComodidadResponseDTO actualizar(Long id, CabanaComodidadRequestDTO r);

    CabanaComodidadResponseDTO buscar(Long id);

    void eliminar(Long id);

    List<CabanaComodidadResponseDTO> listar();
}
