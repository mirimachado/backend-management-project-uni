package br.com.project.backend.uni.modal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    public EmailService() {

    }

    public String sendEmail(String subject, String person, String text) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(person);
            simpleMailMessage.setText(text);
            simpleMailMessage.setSubject(subject);
            javaMailSender.send(simpleMailMessage);
            return "E-mail enviado";
        } catch (Exception e) {
            return "Erro ao tentar enviar e-mail." + e.getMessage();
        }
    }
}