package mirbozorgi.base.context.aop;

import mirbozorgi.base.context.CurrentContext;
import mirbozorgi.base.context.CurrentContextService;
import mirbozorgi.base.context.ThreadLocal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class GamePropertiesAspect {


  @Autowired
  CurrentContextService currentContextService;

  @Before("@annotation(mirbozorgi.base.context.aop.anotions.GameProperties)")
  public void auth() {

    RequestAttributes requestAttributes =
        RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;
    String gamePackageName = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("gamePackageName");

    String env = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("env");

    String marketName = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("marketName");

    CurrentContext currentContext = new CurrentContext(
        gamePackageName,
        env,
        marketName
    );
    ThreadLocal.setCurrentContext(currentContext);
  }


}
