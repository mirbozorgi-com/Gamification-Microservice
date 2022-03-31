package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SocialFollowBeforeException extends CustomException {

  public SocialFollowBeforeException() {
    super("you_followed_this_social_before", HttpStatus.FORBIDDEN);
    this.setData("you_followed_this_social_before");
  }
}
