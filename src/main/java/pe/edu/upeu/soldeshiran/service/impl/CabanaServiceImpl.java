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
public class CabanaServiceImpl implements CabanaService {
    private final CabanaRepository cabanaRepository;
    private final ReservaRepository reservaRepository;
    private final CabanaMapper mapper;

    public CabanaServiceImpl(CabanaRepository cabanaRepository, ReservaRepository reservaRepository, CabanaMapper cabanaMapper) {
        this.cabanaRepository = cabanaRepository;
        this.reservaRepository = reservaRepository;
        this.mapper = cabanaMapper;
    }

    private Cabana obtener(Long id) {
        return cabanaRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Cabana no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public CabanaResponseDTO crear(CabanaRequestDTO r) {
        Cabana e = new Cabana();
        asignar(e, r);
        return mapper.toResponse(cabanaRepository.save(e));
    }

    @Override
    @Transactional
    public CabanaResponseDTO actualizar(Long id, CabanaRequestDTO r) {
        Cabana e = obtener(id);
        asignar(e, r);
        return mapper.toResponse(cabanaRepository.saveAndFlush(e));
    }

    private void asignar(Cabana e, CabanaRequestDTO r) {
        e.setNombre(r.getNombre());
        e.setDescripcion(r.getDescripcion());
        e.setAforoMaximo(r.getAforoMaximo());
        e.setNumHabitaciones(r.getNumHabitaciones());
        e.setNumBanos(r.getNumBanos());
        e.setPrecioBaseNoche(r.getPrecioBaseNoche());
        e.setEstado(r.getEstado());
        e.setImagenPrincipal(r.getImagenPrincipal());
    }

    @Override
    @Transactional(readOnly = true)
    public CabanaResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CabanaResponseDTO> listar() {
        return cabanaRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Cabana e = obtener(id);
        if (reservaRepository.existsByCabanaId(id))
            throw new ReglaNegocioException("No se puede eliminar Cabana porque tiene registros relacionados");
        cabanaRepository.delete(e);
    }
}