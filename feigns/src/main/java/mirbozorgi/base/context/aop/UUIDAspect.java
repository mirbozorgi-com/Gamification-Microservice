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
public class UUIDAspect {

  @Autowired
  CurrentContextService currentContextService;

  @Before("@annotation(mirbozorgi.base.context.aop.anotions.UUID)")
  public void uuId() {

    RequestAttributes requestAttributes =
        RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;
    String uuid = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("uuid");

    CurrentContext currentContext = new CurrentContext(
        uuid
    );
    ThreadLocal.setCurrentContext(currentContext);
  }

}
