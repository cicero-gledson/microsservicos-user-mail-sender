package tech.gtech.mail.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMq {
    private static final String QUEUE_NAME = "email-queue";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public JacksonJsonMessageConverter  messageConverter(){
        return new JacksonJsonMessageConverter();
    }
}
