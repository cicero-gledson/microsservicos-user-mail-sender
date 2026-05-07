package tech.gtech.mail.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.gtech.mail.domain.enums.EmailStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_email")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailModel {

    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    @Column(columnDefinition = "TEXT")
    private String emailBody;
    private EmailStatus emailStatus;
    private LocalDateTime sendDateTimeEmail;

}
