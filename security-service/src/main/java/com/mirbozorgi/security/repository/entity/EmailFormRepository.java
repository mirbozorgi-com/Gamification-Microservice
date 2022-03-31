package com.mirbozorgi.security.repository.entity;

import com.mirbozorgi.security.entity.EmailForm;

public interface EmailFormRepository {

  EmailForm find(
      String gamePackageName,
      String env,
      String name);

}
