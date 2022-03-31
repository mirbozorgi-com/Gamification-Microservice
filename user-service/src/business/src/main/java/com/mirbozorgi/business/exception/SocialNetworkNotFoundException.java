package com.mirbozorgi.business.exception;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SocialNetworkNotFoundException extends CustomException {

  public SocialNetworkNotFoundException() {
    super("the_game_does_not_have_this_social_network!", HttpStatus.NOT_FOUND);
    this.setData("the_game_does_not_have_this_social_network!");
  }
}
