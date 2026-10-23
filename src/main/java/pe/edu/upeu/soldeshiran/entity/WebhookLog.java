package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "webhook_log")
@Getter
@Setter
@NoArgsConstructor
public class WebhookLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_transaccion", nullable = true)
    private TransaccionPago transaccion;
    @Column(name = "id_transaccion_pasarela", length = 100)
    private String idTransaccionPasarela;
    @Lob
    @Column(name = "payload_json", nullable = false)
    private String payloadJson;
    @Column(name = "estado_procesamiento", length = 30)
    private String estadoProcesamiento;
    @Column(name = "fecha_recepcion", updatable = false)
    private LocalDateTime fechaRecepcion;

    @PrePersist
    public void defaults() {
        if (estadoProcesamiento == null) estadoProcesamiento = "PROCESADO_EXITO";
        if (fechaRecepcion == null) fechaRecepcion = LocalDateTime.now();
    }
}
