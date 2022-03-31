package com.mirbozorgi.security.mailService;

public interface EmailService {

  void sendEmail(String from, String to, String subject, String content);

}
