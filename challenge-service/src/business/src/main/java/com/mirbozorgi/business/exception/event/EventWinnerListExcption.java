package com.mirbozorgi.business.exception.event;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class EventWinnerListExcption extends CustomException {

  public EventWinnerListExcption() {
    super("the_user_has_already_been_added_to_the_list_of_winners", HttpStatus.NOT_ACCEPTABLE);
    this.setData("the_user_has_already_been_added_to_the_list_of_winners");
  }
}
