//package mirbozorgi.base.sentry;
//
//import io.sentry.Sentry;
//import io.sentry.SentryClient;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import javax.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.ServletContextInitializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//@Configuration
//public class SentryConfig {
//
//  @Bean
//  public HandlerExceptionResolver sentryExceptionResolver() {
//    return new io.sentry.spring.SentryExceptionResolver();
//  }
//
//  @Bean
//  public ServletContextInitializer sentryServletContextInitializer() {
//    return new io.sentry.spring.SentryServletContextInitializer();
//  }
//
//  @Value("${com.mirbozorgi.sentry.dsn:#{null}}")
//  private String sentryDsn;
//
//  @Value("${com.mirbozorgi.sentry.key1:#{null}}")
//  private String key1;
//
//
//  @Value("${com.mirbozorgi.sentry.key2:#{null}}")
//  private String key2;
//
//  @Value("${com.mirbozorgi.sentry.value1:#{null}}")
//  private String value1;
//
//  @Value("${com.mirbozorgi.sentry.value2:#{null}}")
//  private String value2;
//
//
//  @PostConstruct
//  private void initializeSentry() {
//    if (sentryDsn != null) {
//      SentryClient sentryClient = Sentry.init(sentryDsn);
//      sentryClient.addTag(key1, value1);
//      sentryClient.addTag(key2, value2);
//      sentryClient.addTag("timeDate", LocalDateTime.now().toString());
//      sentryClient.addTag("date", LocalDate.now().toString());
//    }
//  }
//
//}
