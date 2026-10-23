package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cabana", nullable = false)
    private Cabana cabana;
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate fechaIngreso;
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;
    @Column(name = "cantidad_huespedes", precision = 10, scale = 0, nullable = false)
    private Long cantidadHuespedes;
    @Column(name = "precio_noche_aplicado", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioNocheAplicado;
    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoTotal;
    @Column(name = "monto_adelanto", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoAdelanto;
    @Column(name = "estado_reserva", length = 30)
    private String estadoReserva;
    @Column(name = "expira_en", nullable = false)
    private LocalDateTime expiraEn;
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void defaults() {
        if (estadoReserva == null) estadoReserva = "PENDIENTE_PAGO";
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    }
}
