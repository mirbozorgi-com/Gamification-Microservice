package com.mirbozorgi.core.docuemnt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirbozorgi.core.domain.UserLastSpinData;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_last_spin")

@CompoundIndex(
    name = "UK_USER_LAST_SPIN_UUID",
    unique = true,
    def = "{ 'uuid':1}")
public class UserLastSpin {


  @Id
  private ObjectId id;

  private String uuid;

  //key: gamePackageName.env.market.key
  private Map<String, Map<String, Map<String, Map<String, UserLastSpinData>>>> lastTimeOfSpin;

  public UserLastSpin(String uuid,
      Map<String, Map<String, Map<String, Map<String, UserLastSpinData>>>> lastTimeOfSpin) {
    this.uuid = uuid;
    this.lastTimeOfSpin = lastTimeOfSpin;
  }

  public UserLastSpin() {
    lastTimeOfSpin = new HashMap<>();
  }

  public ObjectId getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public Map<String, Map<String, Map<String, Map<String, UserLastSpinData>>>> getLastTimeOfSpin() {
    return lastTimeOfSpin;
  }

  @JsonIgnore
  public String getStringId() {
    return id.toString();
  }

}
