package feigns.feignserror.controller;

import feigns.feignserror.repository.DBHealthRepository;
import feigns.feignserror.repository.memory.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
public class HealthController {


  @Autowired
  private MonitoringRepository monitoringRepository;

  @Autowired
  private DBHealthRepository dbHealthRepository;


  @RequestMapping(value = "/health", method = RequestMethod.GET, produces = "text/plain")
  public String monitor() {

    int db_check = 0;
    int redis_check = 0;
    int app_check = 1;

    StringBuilder builder = new StringBuilder();

    try {
      redis_check = monitoringRepository.check() ? 1 : 0;
    } catch (Exception ex) {
    }

    try {
      db_check = dbHealthRepository.check() > 0 ? 1 : 0;
    } catch (Exception ex) {
    }

    String result =
        String.format(
            "mirbozorgi_app %d \n" + "mirbozorgi_db %d \n" + "mirbozorgi_redis %d \n",
            app_check, db_check, redis_check);

    builder.append(result);

    return builder.toString();
  }
}
