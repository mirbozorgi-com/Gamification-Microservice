package feigns.feignserror.domain;

import java.net.URI;

public class ServiceInstanceResponse {

  private String serviceId;

  private String instanceId;

  private String host;

  private int port;

  private boolean secure;

  private URI uri;

  private String status;


  public String getServiceId() {
    return serviceId;
  }

  public String getInstanceId() {
    return instanceId;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public boolean isSecure() {
    return secure;
  }

  public URI getUri() {
    return uri;
  }


  public String getStatus() {
    return status;
  }

  public ServiceInstanceResponse(String serviceId, String instanceId, String host, int port,
      boolean secure, URI uri, String status) {
    this.serviceId = serviceId;
    this.instanceId = instanceId;
    this.host = host;
    this.port = port;
    this.secure = secure;
    this.uri = uri;
    this.status = status;
  }
}
