package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.entity.Config;
import java.util.List;

public interface ConfigRepository {

  List<Config> getAll();

}