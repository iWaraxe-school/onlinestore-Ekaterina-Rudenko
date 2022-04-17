package by.issoft.store.factory;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.database.DataBase;
import by.issoft.store.helper.RandomStorePopulator;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class DatabaseApp implements Application {

  DataBase dataBase = new DataBase();

  @Override
  public void fillStoreWithProducts(Store store) throws SQLException {
    Map<Category, Integer> categoryIntegerMap = store.getCategoryIntegerMap();
    dataBase.createDbTables();
    for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
      dataBase.insertCategory(entry.getKey());
      for (int i = 0; i < entry.getValue(); i++) {
        Product product = RandomStorePopulator.generateProduct();
        dataBase.insertProduct(product, entry.getKey());
      }
    }
  }

  @Override
  public void defineAppInterface() {
    System.out.println(
        "Enter one of the category name (Bike/Milk/Phone) to show the products from selected category...");
    System.out.println("Enter \"All\" to show the whole store ...");
    System.out.println("Enter \"quit\" to quit the app");
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Enter command: ");
      String command = scanner.next();
      if (command.equals("Bike") || command.equals("Milk") || command.equals("Phone")) {
        System.out.println("Showing the products by category: " + command);
        dataBase.showStoreByCategory(command);
      } else if (command.equals("All")) {
        System.out.println("Showing all the products");
        dataBase.showStore();
      } else if (command.equals("quit")) {
        dataBase.dropTables();
        System.exit(0);
      } else {
        System.out.println("Wrong command.");
      }
    }
  }
}
