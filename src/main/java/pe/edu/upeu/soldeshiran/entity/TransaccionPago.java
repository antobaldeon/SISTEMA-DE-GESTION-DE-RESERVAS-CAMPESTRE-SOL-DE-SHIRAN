package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "transaccion_pago")
@Getter
@Setter
@NoArgsConstructor
public class TransaccionPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;
    @Column(name = "id_externo_pasarela", length = 100)
    private String idExternoPasarela;
    @Column(name = "monto", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;
    @Column(name = "moneda", length = 10)
    private String moneda;
    @Column(name = "metodo_pago", length = 50)
    private String metodoPago;
    @Column(name = "estado_transaccion", length = 30)
    private String estadoTransaccion;
    @Column(name = "fecha_transaccion", updatable = false)
    private LocalDateTime fechaTransaccion;

    @PrePersist
    public void defaults() {
        if (moneda == null) moneda = "PEN";
        if (estadoTransaccion == null) estadoTransaccion = "PENDIENTE";
        if (fechaTransaccion == null) fechaTransaccion = LocalDateTime.now();
    }
}
