package mirbozorgi.base.context.aop;


import mirbozorgi.base.context.CurrentContext;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.ThreadLocal;
import mirbozorgi.base.exception.SessionIdException;
import mirbozorgi.base.feignService.GameFeignService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class GamePropertiesWithUuIdDefaultMarketAspect {


  @Autowired
  CurrentContextService currentContextService;

  @Autowired
  GameFeignService gameFeignService;

  @Before("@annotation(mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuIdDefaultMarket)")
  public void defaultMarket() {
    RequestAttributes requestAttributes =
        RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;

    String exception = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("exception");

    if (exception != null) {
      if (exception.equals("403")) {
        throw new SessionIdException();
      }
    }

    String uuid = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("uuid");

    String gamePackageName = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("gamePackageName");

    String env = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("env");

    String setStrictMarket = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("setStrictMarket");

    String marketName = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("marketName");

    String defaultMarket = null;
    if (setStrictMarket != null && setStrictMarket.equals("true")) {
      defaultMarket = marketName;

    } else {
      defaultMarket = gameFeignService.getDefaultMarket(gamePackageName, env);

    }

    CurrentContext currentContext = new CurrentContext(
        gamePackageName,
        env,
        defaultMarket,
        uuid
    );
    ThreadLocal.setCurrentContext(currentContext);
  }
}
