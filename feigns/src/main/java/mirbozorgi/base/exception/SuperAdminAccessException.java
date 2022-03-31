package mirbozorgi.base.exception;

import org.springframework.http.HttpStatus;

public class SuperAdminAccessException extends CustomException {

  public SuperAdminAccessException() {
    super("You-do-not-have-admin-access", HttpStatus.UNAUTHORIZED);
  }
}
