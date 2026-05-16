package tech.gtech.mail.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.gtech.mail.domain.EmailModel;
import tech.gtech.mail.domain.enums.EmailStatus;
import tech.gtech.mail.dto.EmailDto;
import tech.gtech.mail.repositories.EmailRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public EmailModel sendEmail(EmailDto emailDto) {
        System.out.println(emailDto);

        EmailModel emailModel = new EmailModel();
       // emailModel.setEmailId(UUID.randomUUID());
        emailModel.setUserId(emailDto.userId());
        emailModel.setEmailFrom(emailFrom);
        emailModel.setEmailTo(emailDto.emailTo());
        emailModel.setEmailSubject(emailDto.emailSubject());
        emailModel.setEmailBody(emailDto.emailBody());
        emailModel.setEmailStatus(EmailStatus.PENDING);
        emailModel.setSendDateTimeEmail(LocalDateTime.now());

        try {
            // Monta e envia a mensagem
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(emailFrom);
            helper.setTo(emailDto.emailTo());
            helper.setSubject(emailDto.emailSubject());
            helper.setText(emailDto.emailBody(), true); // true = HTML content

            javaMailSender.send(message);

            // Atualiza status para SENT após envio bem-sucedido
            emailModel.setEmailStatus(EmailStatus.SENT);
            log.info("E-mail enviado com sucesso para: {}", emailDto.emailTo());

        } catch (MessagingException e) {
            // Captura erros de envio e marca como FAILED
            emailModel.setEmailStatus(EmailStatus.FAILED);
            log.error("Erro ao enviar e-mail para {}: {}", emailDto.emailTo(), e.getMessage(), e);

        } catch (Exception e) {
            // Captura qualquer outra exceção inesperada
            emailModel.setEmailStatus(EmailStatus.FAILED);
            log.error("Erro inesperado ao processar e-mail para {}: {}", emailDto.emailTo(), e.getMessage(), e);
        }

        // Salva o registro no banco (independente se foi enviado ou não)
        emailModel = emailRepository.save(emailModel);
        return emailModel;
    }

    // Método auxiliar para buscar e-mail por ID
    public Optional<EmailModel> findEmailById(UUID emailId) {
        return emailRepository.findById(emailId);
    }

    // Método auxiliar para listar e-mails por usuário
    public EmailModel findEmailsByUserId(UUID userId) {
        return emailRepository.findByUserId(userId);
    }
}