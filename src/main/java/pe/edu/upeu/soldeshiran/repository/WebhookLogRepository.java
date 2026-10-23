package pe.edu.upeu.soldeshiran.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.soldeshiran.entity.WebhookLog;

public interface WebhookLogRepository extends JpaRepository<WebhookLog, Long> {
    boolean existsByTransaccionId(Long id);
}
