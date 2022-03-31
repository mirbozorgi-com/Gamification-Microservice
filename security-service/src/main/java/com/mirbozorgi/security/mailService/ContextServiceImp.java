package com.mirbozorgi.security.mailService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ContextServiceImp implements ContextService {

  @Value("${spring.mail.username}")
  private String emailFrom;
  private String forgetPasswordAPI;
  private String goToHommeApi;

  @Override
  public String goToHommeApi() {
    return goToHommeApi;
  }

  @Override
  public String getForgetPasswordAPI() {
    return forgetPasswordAPI;
  }

  @Override
  public String getEmailFrom() {
    return emailFrom;
  }
}

