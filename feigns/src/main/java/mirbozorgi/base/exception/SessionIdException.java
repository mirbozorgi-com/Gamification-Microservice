package mirbozorgi.base.exception;

import org.springframework.http.HttpStatus;

public class SessionIdException extends CustomException {

  public SessionIdException() {
    super("token_expired", HttpStatus.FORBIDDEN);
    this.setData("please_login!");
  }
}
