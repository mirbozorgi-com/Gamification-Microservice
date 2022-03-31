package feigns.feignserror.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feigns")
public class FeignErrors {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;


  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "error")
  private int error;

  @Column(name = "time_of_restart")
  private int timeOfRestart;


  @Column(name = "last_restart")
  private LocalDateTime lastRestart;

  @Column(name = "last_error")
  private LocalDateTime lastError;


  public FeignErrors(String name, int error, LocalDateTime lastRestart,
      LocalDateTime lastError,
      int timeOfRestart) {
    this.name = name;
    this.error = error;
    this.lastRestart = lastRestart;
    this.lastError = lastError;
    this.timeOfRestart = timeOfRestart;
  }

  public FeignErrors() {
  }

  public int getTimeOfRestart() {
    return timeOfRestart;
  }

  public LocalDateTime getLastError() {
    return lastError;
  }

  public String getName() {
    return name;
  }

  public int getError() {
    return error;
  }

  public LocalDateTime getLastRestart() {
    return lastRestart;
  }

  public int getId() {
    return id;
  }
}
