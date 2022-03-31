package mirbozorgi.base.context;

public class ThreadLocal {

  private final static CurrentContext DEFAULT_CONTEXT;

  private static java.lang.ThreadLocal<CurrentContext> currentContext;

  static {
    DEFAULT_CONTEXT = new CurrentContext();
    currentContext = java.lang.ThreadLocal.withInitial(() -> DEFAULT_CONTEXT);
  }

  public static void setCurrentContext(CurrentContext context) {
    currentContext.set(context);
  }

  public static CurrentContext getCurrentContext() {
    return currentContext.get();
  }

}
