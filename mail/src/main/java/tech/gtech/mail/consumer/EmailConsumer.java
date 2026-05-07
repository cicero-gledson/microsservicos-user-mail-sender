package tech.gtech.mail.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import tech.gtech.mail.domain.EmailModel;
import tech.gtech.mail.dto.EmailDto;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "email-queue")
    public void listenEmailQueue(@Payload EmailDto emailDto) {
        System.out.println(emailDto);
    }
}
