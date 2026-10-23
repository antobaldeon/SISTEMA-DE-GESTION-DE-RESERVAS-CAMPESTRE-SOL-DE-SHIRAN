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
public class ReservaServiceImpl implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final CabanaRepository cabanaRepository;
    private final TransaccionPagoRepository transaccionPagoRepository;
    private final ReservaMapper mapper;

    public ReservaServiceImpl(ReservaRepository reservaRepository, CabanaRepository cabanaRepository, TransaccionPagoRepository transaccionPagoRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.cabanaRepository = cabanaRepository;
        this.transaccionPagoRepository = transaccionPagoRepository;
        this.mapper = reservaMapper;
    }

    private Reserva obtener(Long id) {
        return reservaRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public ReservaResponseDTO crear(ReservaRequestDTO r) {
        Reserva e = new Reserva();
        asignar(e, r);
        return mapper.toResponse(reservaRepository.save(e));
    }

    @Override
    @Transactional
    public ReservaResponseDTO actualizar(Long id, ReservaRequestDTO r) {
        Reserva e = obtener(id);
        asignar(e, r);
        return mapper.toResponse(reservaRepository.saveAndFlush(e));
    }

    private void asignar(Reserva e, ReservaRequestDTO r) {
        e.setIdUsuario(r.getIdUsuario());
        e.setFechaIngreso(r.getFechaIngreso());
        e.setFechaSalida(r.getFechaSalida());
        e.setCantidadHuespedes(r.getCantidadHuespedes());
        e.setPrecioNocheAplicado(r.getPrecioNocheAplicado());
        e.setMontoTotal(r.getMontoTotal());
        e.setMontoAdelanto(r.getMontoAdelanto());
        e.setEstadoReserva(r.getEstadoReserva());
        e.setExpiraEn(r.getExpiraEn());
        e.setCabana(cabanaRepository.findById(r.getIdCabana()).orElseThrow(() -> new RecursoNoEncontradoException("Cabana no encontrado con id: " + r.getIdCabana())));
        if (r.getCantidadHuespedes() > e.getCabana().getAforoMaximo())
            throw new ReglaNegocioException("La cantidad de huéspedes supera el aforo máximo de la cabaña");
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> listar() {
        return reservaRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        Reserva e = obtener(id);
        if (transaccionPagoRepository.existsByReservaId(id))
            throw new ReglaNegocioException("No se puede eliminar Reserva porque tiene registros relacionados");
        reservaRepository.delete(e);
    }
}