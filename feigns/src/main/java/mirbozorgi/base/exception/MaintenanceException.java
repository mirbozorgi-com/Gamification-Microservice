package mirbozorgi.base.exception;


public class MaintenanceException extends CustomException {

  public MaintenanceException() {
    super("maintenance_mode");
  }

  public MaintenanceException(String msg) {
    this();
    this.setMsg(msg);
  }
}
