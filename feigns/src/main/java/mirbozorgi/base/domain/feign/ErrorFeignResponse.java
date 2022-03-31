package mirbozorgi.base.domain.feign;

public class ErrorFeignResponse {

  private int error;

  public ErrorFeignResponse(int error) {
    this.error = error;
  }

  public ErrorFeignResponse() {
  }

  public int getError() {
    return error;
  }
}
