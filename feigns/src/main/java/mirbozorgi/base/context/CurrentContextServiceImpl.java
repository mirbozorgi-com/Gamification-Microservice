package mirbozorgi.base.context;

import org.springframework.stereotype.Service;

@Service
public class CurrentContextServiceImpl implements CurrentContextService {

  @Override
  public long getCurrentUserId() {
    return ThreadLocal.getCurrentContext().getUserId();
  }

  @Override
  public String getCurrentUuId() {
    return ThreadLocal.getCurrentContext().getUuid();
  }

  @Override
  public String getCurrentRole() {
    return ThreadLocal.getCurrentContext().getRole();
  }

  @Override
  public String getCurrentGamePackageName() {
    return ThreadLocal.getCurrentContext().getGamePackageName();

  }

  @Override
  public String getCurrentEnv() {
    return ThreadLocal.getCurrentContext().getEnv();
  }

  @Override
  public String getCurrentMarket() {
    return ThreadLocal.getCurrentContext().getMarket();
  }


}
