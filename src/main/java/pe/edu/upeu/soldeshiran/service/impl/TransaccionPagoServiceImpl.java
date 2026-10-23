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
public class TransaccionPagoServiceImpl implements TransaccionPagoService {
    private final TransaccionPagoRepository transaccionPagoRepository;
    private final ReservaRepository reservaRepository;
    private final WebhookLogRepository webhookLogRepository;
    private final TransaccionPagoMapper mapper;

    public TransaccionPagoServiceImpl(TransaccionPagoRepository transaccionPagoRepository, ReservaRepository reservaRepository, WebhookLogRepository webhookLogRepository, TransaccionPagoMapper transaccionPagoMapper) {
        this.transaccionPagoRepository = transaccionPagoRepository;
        this.reservaRepository = reservaRepository;
        this.webhookLogRepository = webhookLogRepository;
        this.mapper = transaccionPagoMapper;
    }

    private TransaccionPago obtener(Long id) {
        return transaccionPagoRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("TransaccionPago no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public TransaccionPagoResponseDTO crear(TransaccionPagoRequestDTO r) {
        validarExterno(r.getIdExternoPasarela(), null);
        TransaccionPago e = new TransaccionPago();
        asignar(e, r);
        return mapper.toResponse(transaccionPagoRepository.save(e));
    }

    @Override
    @Transactional
    public TransaccionPagoResponseDTO actualizar(Long id, TransaccionPagoRequestDTO r) {
        TransaccionPago e = obtener(id);
        validarExterno(r.getIdExternoPasarela(), id);
        asignar(e, r);
        return mapper.toResponse(transaccionPagoRepository.saveAndFlush(e));
    }

    private void validarExterno(String valor, Long id) {
        if (valor == null || valor.isBlank()) return;
        boolean duplicado = id == null ? transaccionPagoRepository.existsByIdExternoPasarela(valor) : transaccionPagoRepository.existsByIdExternoPasarelaAndIdNot(valor, id);
        if (duplicado) throw new ReglaNegocioException("Ya existe una transaccion con ese identificador externo");
    }

    private void asignar(TransaccionPago e, TransaccionPagoRequestDTO r) {
        e.setIdExternoPasarela(r.getIdExternoPasarela());
        e.setMonto(r.getMonto());
        e.setMoneda(r.getMoneda());
        e.setMetodoPago(r.getMetodoPago());
        e.setEstadoTransaccion(r.getEstadoTransaccion());
        e.setReserva(reservaRepository.findById(r.getIdReserva()).orElseThrow(() -> new RecursoNoEncontradoException("Reserva no encontrado con id: " + r.getIdReserva())));
    }

    @Override
    @Transactional(readOnly = true)
    public TransaccionPagoResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransaccionPagoResponseDTO> listar() {
        return transaccionPagoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        TransaccionPago e = obtener(id);
        if (webhookLogRepository.existsByTransaccionId(id))
            throw new ReglaNegocioException("No se puede eliminar TransaccionPago porque tiene registros relacionados");
        transaccionPagoRepository.delete(e);
    }
}