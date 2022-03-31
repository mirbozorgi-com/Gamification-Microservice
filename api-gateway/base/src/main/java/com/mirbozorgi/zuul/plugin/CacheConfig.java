package com.mirbozorgi.zuul.plugin;//package com.mirbozorgi.api.plugin;
//
//import com.github.benmanes.caffeine.cache.Caffeine;
//import com.github.benmanes.caffeine.cache.Ticker;
//import java.util.Arrays;
//import java.util.concurrent.TimeUnit;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.caffeine.CaffeineCache;
//import org.springframework.cache.support.SimpleCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CacheConfig {
//
//  /**
//   * cache manager.
//   */
//  @Bean
//  public CacheManager cacheManager(Ticker ticker) {
//    CaffeineCache baseDataCache = buildCache("base", ticker, 30, 100);
//    SimpleCacheManager manager = new SimpleCacheManager();
//    manager.setCaches(Arrays.asList(baseDataCache));
//    return manager;
//  }
//
//  private CaffeineCache buildCache(String name, Ticker ticker, int secondsToExpire, long maxSize) {
//    return new CaffeineCache(name, Caffeine.newBuilder()
//        .expireAfterWrite(secondsToExpire, TimeUnit.SECONDS)
//        .maximumSize(maxSize)
//        .ticker(ticker)
//        .build());
//  }
//
//  @Bean
//  public Ticker ticker() {
//    return Ticker.systemTicker();
//  }
//
//}