package mirbozorgi.base.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@FeignClient(name = "kafka-service")
public interface KafkaFeign {

  @RequestMapping(method = RequestMethod.POST, path = "send-message/string")
  Object publishMessageString(@RequestBody String payload);

}
