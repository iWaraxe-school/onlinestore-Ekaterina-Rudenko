package by.issoft.store.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AppFactory {

  final static Map<AppType, Supplier<Application>> APPLICATION_TYPE_MAP = new HashMap<>() {
    {
      put(AppType.CONSOLE, ConsoleApp::new);
      put(AppType.DATABASE, DatabaseApp::new);
      put(AppType.HTTP, HttpApp::new);
    }
  };

  public static Application getApplication(AppType type) {
    return type != null ? APPLICATION_TYPE_MAP.get(type).get() : null;
  }

}
