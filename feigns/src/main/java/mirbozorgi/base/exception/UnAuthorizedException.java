package mirbozorgi.base.exception;


import org.springframework.http.HttpStatus;

public class UnAuthorizedException extends CustomException {

  public UnAuthorizedException() {
    super("unauthorized", HttpStatus.UNAUTHORIZED);
  }

}
