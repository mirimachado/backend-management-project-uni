package br.com.project.backend.uni.modal.services.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    private static final String TEMPLATE_NAME = "registration";
    private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
    private static final String PNG_MIME = "image/png";
    private static final String MAIL_SUBJECT = "Confirmação de alteração de senha";

    private final Environment enviroment;
    private final TemplateEngine htmlTemplateEngine;

    public EmailService(Environment enviroment, TemplateEngine htmlTemplateEngine, JavaMailSender mailSender) {

      this.enviroment = enviroment;
      this.htmlTemplateEngine = htmlTemplateEngine;
      this.javaMailSender = mailSender;
    }

    public void sendMailWithInline(String user) throws MessagingException, UnsupportedEncodingException {
        String confirmationUrl = "genereted_confirmation_url";
        String mailFrom = enviroment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = enviroment.getProperty("mail.from.name","Uni Velha Central");

        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper email;
        email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setTo(user);
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final Context context = new Context(LocaleContextHolder.getLocale());
        context.setVariable("email", user);
        context.setVariable("springLogo", SPRING_LOGO_IMAGE);
        context.setVariable("url", confirmationUrl);

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, context);
        email.setText(htmlContent, true);

        ClassPathResource classPathResource = new ClassPathResource(SPRING_LOGO_IMAGE);
        email.addInline("springLogo", classPathResource, PNG_MIME);

        javaMailSender.send(mimeMessage);

    }

}