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
public class WebhookLogServiceImpl implements WebhookLogService {
    private final WebhookLogRepository webhookLogRepository;
    private final TransaccionPagoRepository transaccionPagoRepository;
    private final WebhookLogMapper mapper;

    public WebhookLogServiceImpl(WebhookLogRepository webhookLogRepository, TransaccionPagoRepository transaccionPagoRepository, WebhookLogMapper webhookLogMapper) {
        this.webhookLogRepository = webhookLogRepository;
        this.transaccionPagoRepository = transaccionPagoRepository;
        this.mapper = webhookLogMapper;
    }

    private WebhookLog obtener(Long id) {
        return webhookLogRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("WebhookLog no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public WebhookLogResponseDTO crear(WebhookLogRequestDTO r) {
        WebhookLog e = new WebhookLog();
        asignar(e, r);
        return mapper.toResponse(webhookLogRepository.save(e));
    }

    @Override
    @Transactional
    public WebhookLogResponseDTO actualizar(Long id, WebhookLogRequestDTO r) {
        WebhookLog e = obtener(id);
        asignar(e, r);
        return mapper.toResponse(webhookLogRepository.saveAndFlush(e));
    }

    private void asignar(WebhookLog e, WebhookLogRequestDTO r) {
        e.setIdTransaccionPasarela(r.getIdTransaccionPasarela());
        e.setPayloadJson(r.getPayloadJson());
        e.setEstadoProcesamiento(r.getEstadoProcesamiento());
        e.setTransaccion(r.getIdTransaccion() == null ? null : transaccionPagoRepository.findById(r.getIdTransaccion()).orElseThrow(() -> new RecursoNoEncontradoException("TransaccionPago no encontrado con id: " + r.getIdTransaccion())));
    }

    @Override
    @Transactional(readOnly = true)
    public WebhookLogResponseDTO buscar(Long id) {
        return mapper.toResponse(obtener(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WebhookLogResponseDTO> listar() {
        return webhookLogRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        WebhookLog e = obtener(id);
        webhookLogRepository.delete(e);
    }
}