package mirbozorgi.base.context;

public interface CurrentContextService {

  long getCurrentUserId();

  String getCurrentUuId();

  String getCurrentRole();

  String getCurrentGamePackageName();

  String getCurrentEnv();

  String getCurrentMarket();


}
