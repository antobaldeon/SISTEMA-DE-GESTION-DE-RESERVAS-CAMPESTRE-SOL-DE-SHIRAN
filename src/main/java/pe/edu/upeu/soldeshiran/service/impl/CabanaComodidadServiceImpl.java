package pe.edu.upeu.soldeshiran.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.soldeshiran.dto.*;
import pe.edu.upeu.soldeshiran.entity.*;
import pe.edu.upeu.soldeshiran.mapper.*;
import pe.edu.upeu.soldeshiran.repository.*;
import pe.edu.upeu.soldeshiran.exception.*;

@Service
public class CabanaComodidadServiceImpl implements CabanaComodidadService {
    private final CabanaComodidadRepository cabanaComodidadRepository;
    private final CabanaRepository cabanaRepository;
    private final CabanaComodidadMapper mapper;

    public CabanaComodidadServiceImpl(CabanaComodidadRepository cabanaComodidadRepository, CabanaRepository cabanaRepository, CabanaComodidadMapper cabanaComodidadMapper) {
        this.cabanaComodidadRepository = cabanaComodidadRepository;
        this.cabanaRepository = cabanaRepository;
        this.mapper = cabanaComodidadMapper;
    }

    private CabanaComodidad obtener(Long id) {
        return cabanaComodidadRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("CabanaComodidad no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public CabanaComodidadResponseDTO crear(CabanaComodidadRequestDTO r) {
        CabanaComodidad e = new CabanaComodidad();
        asignar(e, r);
        return mapper.toResponse(cabanaComodidadRepository.save(e));
    }

    @Override
    @Transactional
    public CabanaComodidadResponseDTO actualizar(Long id, CabanaComodidadRequestDTO r) {
        CabanaComodidad e = obtener(id);
        asignar(e, r);
        return mapper.toResponse(cabanaComodidadRepository.saveAndFlush(e));
    }

    private void asignar(CabanaComodidad e, CabanaComodidadRequestDTO r) {
        e.setNombreComodidad(r.getNombreComodidad());
        e.setCabana(cabanaRepository.findById(r.getIdCabana()).orElseThrow(() -> new RecursoNoEncontradoException("Cabana no encontrado con id: " + r.getIdCabana())));
    }

    @Override
    @Transactional(readOnly = true)
    public CabanaComodidadResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CabanaComodidadResponseDTO> listar() {
        return cabanaComodidadRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        CabanaComodidad e = obtener(id);
        cabanaComodidadRepository.delete(e);
    }
}