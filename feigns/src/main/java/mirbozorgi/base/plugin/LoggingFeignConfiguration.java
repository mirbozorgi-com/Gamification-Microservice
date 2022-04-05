//package mirbozorgi.base.plugin;
//
//import ch.qos.logback.classic.AsyncAppender;
//import ch.qos.logback.classic.Level;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.spi.LoggerContextListener;
//import ch.qos.logback.core.spi.ContextAwareBase;
//import ch.qos.logback.core.util.Duration;
//import java.net.InetSocketAddress;
//import net.logstash.logback.appender.LogstashTcpSocketAppender;
//import net.logstash.logback.encoder.LogstashEncoder;
//import net.logstash.logback.stacktrace.ShortenedThrowableConverter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LoggingFeignConfiguration {
//
//  private static final String LOGSTASH_APPENDER_NAME = "LOGSTASH";
//
//  private static final String ASYNC_LOGSTASH_APPENDER_NAME = "ASYNC_LOGSTASH";
//
//  private final Logger log = LoggerFactory.getLogger(LoggingFeignConfiguration.class);
//  private final String serviceNAme;
//  private final boolean logStashEnable;
//  private final String logStashHost;
//  private final int logStashPort;
//  private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//  public LoggingFeignConfiguration(
//      @Value("${logstash.service-name}") String serviceNAme,
//      @Value("${logstash.enable}") boolean logStashEnable,
//      @Value("${logstash.host}") String logStashHost,
//      @Value("${logstash.port}") int logStashPort) {
//    this.serviceNAme = serviceNAme;
//    this.logStashEnable = logStashEnable;
//    this.logStashHost = logStashHost;
//    this.logStashPort = logStashPort;
//
//    if (this.logStashEnable) {
//      addLogstashAppender(context);
//      addContextListener(context);
//    }
//  }
//
//  private void addContextListener(LoggerContext context) {
//    LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
//    loggerContextListener.setContext(context);
//    context.addListener(loggerContextListener);
//  }
//
//  private void addLogstashAppender(LoggerContext context) {
//    log.info("Initializing Logstash logging");
//
//    LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
//    logstashAppender.setKeepAliveDuration(Duration.buildByMinutes(10));
//    logstashAppender.setName(LOGSTASH_APPENDER_NAME);
//    logstashAppender.setContext(context);
//
//    String customFields = String.format("{\"service_name\":\"%s\",\"type\":\"%s\" }",
//        serviceNAme, "application_log");
//
//    LogstashEncoder logstashEncoder = new LogstashEncoder();
//    logstashEncoder.setCustomFields(customFields);
//    logstashAppender.addDestinations(
//        new InetSocketAddress(logStashHost, logStashPort));
//
//    ShortenedThrowableConverter throwableConverter = new ShortenedThrowableConverter();
//    throwableConverter.setRootCauseFirst(true);
//    logstashEncoder.setThrowableConverter(throwableConverter);
//    logstashEncoder.setCustomFields(customFields);
//
//    logstashAppender.setEncoder(logstashEncoder);
//    logstashAppender.start();
//
//    // Wrap the appender in an Async appender for performance
//    AsyncAppender asyncLogstashAppender = new AsyncAppender();
//    asyncLogstashAppender.setContext(context);
//    asyncLogstashAppender.setName(ASYNC_LOGSTASH_APPENDER_NAME);
//    asyncLogstashAppender
//        .setQueueSize(512);
//    asyncLogstashAppender.addAppender(logstashAppender);
//    asyncLogstashAppender.start();
//
//    context.getLogger("ROOT").addAppender(asyncLogstashAppender);
//  }
//
//  /**
//   * Logback configuration is achieved by configuration file and API. When configuration file change
//   * is detected, the configuration is reset. This listener ensures that the programmatic
//   * configuration is also re-applied after reset.
//   */
//  class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {
//
//    @Override
//    public boolean isResetResistant() {
//      return true;
//    }
//
//    @Override
//    public void onStart(LoggerContext context) {
//      addLogstashAppender(context);
//    }
//
//    @Override
//    public void onReset(LoggerContext context) {
//      addLogstashAppender(context);
//    }
//
//    @Override
//    public void onStop(LoggerContext context) {
//      // Nothing to do.
//    }
//
//    @Override
//    public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
//      // Nothing to do.
//    }
//  }
//
//}