package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "cabana_comodidades")
@Getter
@Setter
@NoArgsConstructor
public class CabanaComodidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comodidad")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cabana", nullable = false)
    private Cabana cabana;
    @Column(name = "nombre_comodidad", nullable = false, length = 50)
    private String nombreComodidad;

    @PrePersist
    public void defaults() {
    }
}
