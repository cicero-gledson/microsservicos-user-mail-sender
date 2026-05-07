package tech.gtech.user.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tech.gtech.user.domain.UserModel;
import tech.gtech.user.dto.EmailDto;
import tech.gtech.user.repository.UserRepository;

@Component
public class UserProducer {
    private final RabbitTemplate rabbitTemplate;
    private final String ROUTING_KEY = "email-queue";

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public  void sendUserCreatedEvent (UserModel user) {
        String mensagemBoasVindas = """
                Bem-vindo(a), %s! 🥋
                
                Que bom ter você por aqui! A partir de agora, você tem na palma da mão 
                tudo o que precisa para acompanhar sua jornada e acessar seus dados 
                de forma rápida e prática.
                """;
        mensagemBoasVindas = String.format(mensagemBoasVindas, user.getName());

        EmailDto emailDto = new EmailDto(
               user.getUserId(),
               user.getEmail(),
               "Welcome to Java10x-microsserviços-user-mail-sender",
                mensagemBoasVindas
        );

        rabbitTemplate.convertAndSend("", ROUTING_KEY, emailDto);
    }
}
