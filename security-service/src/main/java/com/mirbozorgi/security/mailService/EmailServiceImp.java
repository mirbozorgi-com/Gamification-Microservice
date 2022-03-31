package com.mirbozorgi.security.mailService;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImp implements EmailService {


  @Autowired
  private JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String emailFrom;

  @Value("${spring.mail.port}")
  private String port;

  @Value("${spring.mail.host}")
  private String host;

  @Value("${spring.mail.password}")
  private String password;


  @Override
  public void sendEmail(String from, String to, String subject, String content) {
    sendMail(from, to, subject, content);
  }

  private void sendSimpleMessage(String from, String to, String subject, String text) {
    try {

      ((JavaMailSenderImpl) emailSender).setHost(host);
      ((JavaMailSenderImpl) emailSender).setPort(Integer.valueOf(port));
      ((JavaMailSenderImpl) emailSender).setUsername(emailFrom);
      ((JavaMailSenderImpl) emailSender).setPassword(password);

      Properties properties = new Properties();
      properties.put("spring.mail.properties.mail.smtp.auth", true);
      properties.put("spring.mail.properties.mail.smtp.starttls.enable", true);
      properties.put("mail.smtp.starttls.enable", true);

      ((JavaMailSenderImpl) emailSender).setJavaMailProperties(properties);

      MimeMessage mimeMessage = emailSender.createMimeMessage();
      MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());
      message.setFrom(from);
      message.setTo(to);
      message.setSubject(subject);
      message.setText(text, true);
      emailSender.send(mimeMessage);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }

  private void sendMail(String from, String to, String subject, String text) {
    sendSimpleMessage(from, to, subject, text);
  }


}



