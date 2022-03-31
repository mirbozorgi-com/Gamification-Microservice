package mirbozorgi.base.domain.user;

import java.util.List;

public class AddAvatarModel {

  private List<Integer> avatarId;

  public AddAvatarModel() {

  }

  public List<Integer> getAvatarId() {
    return avatarId;
  }

  public AddAvatarModel(List<Integer> avatarId) {
    this.avatarId = avatarId;
  }
}
