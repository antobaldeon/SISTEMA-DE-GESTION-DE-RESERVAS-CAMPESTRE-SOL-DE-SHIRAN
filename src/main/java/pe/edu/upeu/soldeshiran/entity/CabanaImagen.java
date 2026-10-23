package pe.edu.upeu.soldeshiran.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "cabana_imagenes")
@Getter
@Setter
@NoArgsConstructor
public class CabanaImagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cabana", nullable = false)
    private Cabana cabana;
    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @PrePersist
    public void defaults() {
    }
}
