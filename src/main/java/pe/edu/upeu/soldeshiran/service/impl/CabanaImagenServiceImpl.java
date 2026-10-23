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
public class CabanaImagenServiceImpl implements CabanaImagenService {
    private final CabanaImagenRepository cabanaImagenRepository;
    private final CabanaRepository cabanaRepository;
    private final CabanaImagenMapper mapper;

    public CabanaImagenServiceImpl(CabanaImagenRepository cabanaImagenRepository, CabanaRepository cabanaRepository, CabanaImagenMapper cabanaImagenMapper) {
        this.cabanaImagenRepository = cabanaImagenRepository;
        this.cabanaRepository = cabanaRepository;
        this.mapper = cabanaImagenMapper;
    }

    private CabanaImagen obtener(Long id) {
        return cabanaImagenRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("CabanaImagen no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public CabanaImagenResponseDTO crear(CabanaImagenRequestDTO r) {
        CabanaImagen e = new CabanaImagen();
        asignar(e, r);
        return mapper.toResponse(cabanaImagenRepository.save(e));
    }

    @Override
    @Transactional
    public CabanaImagenResponseDTO actualizar(Long id, CabanaImagenRequestDTO r) {
        CabanaImagen e = obtener(id);
        asignar(e, r);
        return mapper.toResponse(cabanaImagenRepository.saveAndFlush(e));
    }

    private void asignar(CabanaImagen e, CabanaImagenRequestDTO r) {
        e.setUrlImagen(r.getUrlImagen());
        e.setCabana(cabanaRepository.findById(r.getIdCabana()).orElseThrow(() -> new RecursoNoEncontradoException("Cabana no encontrado con id: " + r.getIdCabana())));
    }

    @Override
    @Transactional(readOnly = true)
    public CabanaImagenResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CabanaImagenResponseDTO> listar() {
        return cabanaImagenRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        CabanaImagen e = obtener(id);
        cabanaImagenRepository.delete(e);
    }
}