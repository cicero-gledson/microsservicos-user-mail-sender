package tech.gtech.mail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.gtech.mail.domain.EmailModel;

import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
    EmailModel findByUserId(UUID userId);
}
