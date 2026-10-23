package pe.edu.upeu.soldeshiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.soldeshiran.entity.TransaccionPago;

public interface TransaccionPagoRepository extends JpaRepository<TransaccionPago, Long> {
    boolean existsByReservaId(Long id);

    boolean existsByIdExternoPasarela(String valor);

    boolean existsByIdExternoPasarelaAndIdNot(String valor, Long id);
}
