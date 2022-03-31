package com.mirbozorgi.reactor.security.constant;

public enum EnumRole {
  ORDINARY("ORDINARY"),
  ADMIN("ADMIN"),
  SUPER_ADMIN("SUPER_ADMIN"),
  USER("USER"),
  GUEST_USER("GUEST_USER");
  private final String text;

  EnumRole(String enumValue) {
    this.text = enumValue;
  }

  public String getText() {
    return this.text;
  }

  public static EnumRole fromString(String text) {
    for (EnumRole b : EnumRole.values()) {
      if (b.text.equalsIgnoreCase(text)) {
        return b;
      }
    }
    return null;
  }
}
