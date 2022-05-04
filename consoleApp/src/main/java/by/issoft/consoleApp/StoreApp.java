package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.factory.AppFactory;
import by.issoft.store.factory.AppType;
import by.issoft.store.factory.Application;
import java.io.IOException;

public class StoreApp {

  public static void main(String[] args) throws InterruptedException, IOException {
    Store store = Store.getInstance();
    Application application = AppFactory.getApplication(AppType.HTTP);
    store.showStoreInfo();
    application.defineAppInterface();
  }
}
