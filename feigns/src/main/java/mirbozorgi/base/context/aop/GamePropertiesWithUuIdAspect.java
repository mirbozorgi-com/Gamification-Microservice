package mirbozorgi.base.context.aop;

import mirbozorgi.base.context.CurrentContext;
import mirbozorgi.base.context.ThreadLocal;
import mirbozorgi.base.exception.SessionIdException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class GamePropertiesWithUuIdAspect {


  @Before("@annotation(mirbozorgi.base.context.aop.anotions.GamePropertiesWithUuId)")
  public void gamePropertiesWithUuId() {

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

    String marketName = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("marketName");

    CurrentContext currentContext = new CurrentContext(
        gamePackageName,
        env,
        marketName,
        uuid
    );
    ThreadLocal.setCurrentContext(currentContext);
  }
}
