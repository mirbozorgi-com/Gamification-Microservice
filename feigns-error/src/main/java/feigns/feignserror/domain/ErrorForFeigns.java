package feigns.feignserror.domain;

import java.time.LocalDateTime;

public class ErrorForFeigns {

  private LocalDateTime time;

  private String massage;


  public LocalDateTime getTime() {
    return time;
  }

  public String getMassage() {
    return massage;
  }

  public ErrorForFeigns(LocalDateTime time,
      String massage) {
    this.time = time;
    this.massage = massage;
  }

  public ErrorForFeigns() {
  }
}
