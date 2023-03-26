package com.demon.HabrMail.service;

import com.demon.HabrMail.model.RegisteredUserMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${from-mail}")
    private String from;

    public MailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @SneakyThrows
    public void sendRegisteredUserMessage(String to, String subject, RegisteredUserMessage registeredUserMessage) {
        Context context = new Context();
        context.setVariable("registeredUserMessage", registeredUserMessage);
        System.out.println(registeredUserMessage.getEmail());
        System.out.println(registeredUserMessage.getLogin());

//        String process = templateEngine.process("mail-template", context);
//        javax.mail.internet.MimeMessage mimeMessage = emailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//        helper.setSubject(subject);
//        helper.setText(process, true);
//        helper.setTo(to);
//
//        emailSender.send(mimeMessage);
    }

}
