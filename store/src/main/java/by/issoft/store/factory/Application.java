package by.issoft.store.factory;

import by.issoft.store.Store;
import java.io.IOException;
import java.sql.SQLException;

public interface Application {

  void fillStoreWithProducts(Store store) throws SQLException;
  void defineAppInterface() throws InterruptedException, IOException;
}
