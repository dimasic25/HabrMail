package com.demon.HabrMail.rabbit;

import com.demon.HabrMail.model.RegisteredUserMessage;
import com.demon.HabrMail.service.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(RabbitConsumer.class);

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    public RabbitConsumer(MailService mailService, ObjectMapper objectMapper) {
        this.mailService = mailService;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @RabbitListener(queues = "${queue-name}")
    public void processMyQueue(String message) {
        LOG.info("Received from myQueue : {} ", new String(message.getBytes()));

        RegisteredUserMessage registeredUserMessage = objectMapper.readValue(message, RegisteredUserMessage.class);

        mailService.sendRegisteredUserMessage(registeredUserMessage.getEmail(), "Subject", registeredUserMessage);
    }

}
