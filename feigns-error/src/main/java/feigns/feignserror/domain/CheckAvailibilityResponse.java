package feigns.feignserror.domain;

import java.util.ArrayList;
import java.util.List;

public class CheckAvailibilityResponse {

  private List<String> downServices;

  private List<String> upServices;

  public CheckAvailibilityResponse(List<String> downServices,
      List<String> upServices) {
    this.downServices = downServices;
    this.upServices = upServices;
  }

  public CheckAvailibilityResponse() {
    downServices = new ArrayList<>();
    upServices = new ArrayList<>();
  }

  public List<String> getDownServices() {
    return downServices;
  }

  public List<String> getUpServices() {
    return upServices;
  }
}
