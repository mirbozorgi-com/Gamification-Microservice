package com.mirbozorgi.core.repository.document;

import com.mirbozorgi.core.document.config.Config;
import java.util.List;

public interface ConfigRepository {

  List<Config> getAll();

}