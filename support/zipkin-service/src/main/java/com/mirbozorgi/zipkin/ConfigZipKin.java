//package demo.zipkin;
//
//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
//import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
//import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
//import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
//import org.springframework.context.annotation.Bean;
//import zipkin.Span;
//
//public class ConfigZipKin {
//
//  @Autowired
//  private EurekaClient eurekaClient;
//
//  @Autowired
//  private SpanMetricReporter spanMetricReporter;
//
//  @Autowired
//  private ZipkinProperties zipkinProperties;
//
//  @Value("(^cleanup.*)")
//  private String skipPattern;
//
//// ... the main method goes here
//
//  @Bean
//  public ZipkinSpanReporter makeZipkinSpanReporter() {
//    return new ZipkinSpanReporter() {
//      private HttpZipkinSpanReporter delegate;
//      private String baseUrl;
//
//      @Override
//      public void report(Span span) {
//
//        InstanceInfo instance = eurekaClient
//            .getNextServerFromEureka("zipkin", true);
//        if (!(baseUrl != null &&
//            instance.getHomePageUrl().equals(baseUrl))) {
//          baseUrl = instance.getHomePageUrl();
//          delegate = new HttpZipkinSpanReporter(
//              baseUrl, zipkinProperties.getFlushInterval(), true, spanMetricReporter);
//          if (!span.name.matches(skipPattern)) {
//            delegate.report(span);
//          }
//        }
//      }
//    };
//  }
//}
//
//
