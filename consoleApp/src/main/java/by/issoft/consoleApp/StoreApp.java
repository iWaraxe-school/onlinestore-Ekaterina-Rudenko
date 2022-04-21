package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.factory.AppFactory;
import by.issoft.store.factory.AppType;
import by.issoft.store.factory.Application;
import java.sql.SQLException;

public class StoreApp {

  public static void main(String[] args) throws SQLException {
    Store store = Store.getInstance();
    Application application = AppFactory.getApplication(AppType.DATABASE);
    application.fillStoreWithProducts(store);
    //store.showStoreInfo();
    application.defineAppInterface();
  }
}
