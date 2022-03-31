package feigns.feignserror.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "micro_service_registry ")
public class MicroServiceRegistry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "level")
  private int level;


  public MicroServiceRegistry(String name, int level) {
    this.name = name;
    this.level = level;
  }

  public MicroServiceRegistry() {
  }

  public int getLevel() {
    return level;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
