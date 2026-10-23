package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "cabana")
@Getter
@Setter
@NoArgsConstructor
public class Cabana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cabana")
    private Long id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Lob
    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    @Column(name = "aforo_maximo", precision = 10, scale = 0, nullable = false)
    private Long aforoMaximo;
    @Column(name = "num_habitaciones", precision = 10, scale = 0, nullable = false)
    private Long numHabitaciones;
    @Column(name = "num_banos", precision = 10, scale = 0, nullable = false)
    private Long numBanos;
    @Column(name = "precio_base_noche", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioBaseNoche;
    @Column(name = "estado", length = 20)
    private String estado;
    @Column(name = "imagen_principal", nullable = false, length = 255)
    private String imagenPrincipal;
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    public void defaults() {
        if (estado == null) estado = "ACTIVA";
        if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    }
}
