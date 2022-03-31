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
public class AuthAspect {


  @Autowired
  CurrentContextService currentContextService;

  @Before("@annotation(mirbozorgi.base.context.aop.anotions.Auth)")
  public void auth() {

    RequestAttributes requestAttributes =
        RequestContextHolder.getRequestAttributes();
    assert requestAttributes != null;
    String userId = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("userId");

    String role = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("role");

    String uuid = ((ServletRequestAttributes) requestAttributes).getRequest().getHeader
        ("uuid");

    CurrentContext currentContext = new CurrentContext(
        Long.parseLong(userId),
        uuid,
        role
    );
    ThreadLocal.setCurrentContext(currentContext);
  }
}
