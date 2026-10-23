package pe.edu.upeu.soldeshiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.soldeshiran.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    boolean existsByCabanaId(Long id);
}
