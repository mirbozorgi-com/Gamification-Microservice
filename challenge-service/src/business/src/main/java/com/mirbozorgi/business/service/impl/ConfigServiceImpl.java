package com.mirbozorgi.business.service.impl;//package com.mirbozorgi.business.service.impl;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import com.mirbozorgi.business.service.ConfigService;
//import com.mirbozorgi.core.entiry.config.Config;
//import com.mirbozorgi.core.model.AppConfig;
//import com.mirbozorgi.core.repository.entiry.ConfigRepository;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ConfigServiceImpl implements ConfigService {
//
//  private static final String DEFAULT_COHORT = "DEF";
//  private static Map<String, AppConfig> CONF_MAP;
//
//  @Autowired
//  ConfigRepository configRepository;
//
//  private Map<String, AppConfig> getConfig() {
//
//    if (CONF_MAP == null) {
//      fill();
//    }
//    return CONF_MAP;
//  }
//
//  private AppConfig cohortConfig(String cohort) {
//    if (getConfig().get(cohort) != null) {
//      return getConfig().get(cohort);
//    }
//    return new AppConfig();
//  }
//
//  @Scheduled(fixedRateString = "${business.config.interval}")
//  private void fill() {
//
//    try {
//      Map<String, AppConfig> newConfs = new HashMap<>();
//
//      List<Config> configDetails = configRepository.getAll();
//      List<String> cohorts = configDetails.stream().map(x -> x.getCohort()).distinct()
//          .collect(Collectors.toList());
//
//      cohorts.forEach(coh -> {
//
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("{");
//
//        configDetails.stream().filter(x -> x.getCohort().equals(coh)).forEach(x -> {
//          sb.append('"' + x.getKey() + '"');
//          sb.append(":");
//          sb.append(x.getValue());
//          sb.append(",");
//        });
//        sb.deleteCharAt(sb.length() - 1);
//        sb.append("}");
//
//        ObjectMapper mapper = new ObjectMapper()
//            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//
//        try {
//          AppConfig conf = mapper.readValue(sb.toString(), AppConfig.class);
//          newConfs.put(coh, conf);
//        } catch (IOException e) {
//
//        }
//
//      });
//      CONF_MAP = newConfs;
//    } catch (Exception e) {
//      CONF_MAP = new HashMap<String, AppConfig>();
//    }
//  }
//
//
//  @Override
//  public Integer clientVersion() {
//    return cohortConfig(DEFAULT_COHORT).getClientVersion();
//  }
//
//  @Override
//  public boolean maintenance() {
//    return cohortConfig(DEFAULT_COHORT).isMaintenanceMode();
//  }
//
//  @Override
//  public Integer[] cohortChance() {
//    return new Integer[0];
//  }
//}