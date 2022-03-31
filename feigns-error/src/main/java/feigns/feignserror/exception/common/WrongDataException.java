package feigns.feignserror.exception.common;

import mirbozorgi.base.exception.CustomException;
import org.springframework.http.HttpStatus;

public class WrongDataException extends CustomException {

  public WrongDataException() {
    super("wrong_data", HttpStatus.BAD_REQUEST);
  }

  public WrongDataException(String msg) {
    this(msg, null);
  }

  public WrongDataException(String msg, Object obj) {
    super("wrong_data", HttpStatus.BAD_REQUEST, msg);
    super.setData(obj);
  }

}
