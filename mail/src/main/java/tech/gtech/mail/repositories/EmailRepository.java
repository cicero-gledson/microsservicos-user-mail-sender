package tech.gtech.mail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.gtech.mail.domain.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {
}
