package by.issoft.store.factory;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.handler.Runner;
import by.issoft.store.helper.RandomStorePopulator;
import by.issoft.store.helper.StoreHelper;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleApp implements Application {

  @Override
  public void fillStoreWithProducts(Store store) {
    StoreHelper storeHelper = new StoreHelper();
    Map<Category, Integer> categoryIntegerMap = storeHelper.defineCategoriesAndQuantities();
    for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
      List<Product> productsToAdd = RandomStorePopulator.generateProductList(entry.getValue());
      entry.getKey().addAllProducts(productsToAdd);
      Store.getInstance().setCategoryList(entry.getKey());
    }
  }

  @Override
  public void defineAppInterface() {
    System.out.println("Running console store application...");
    Runner runner = new Runner();
    String commandsString = """
          \n
        To sort the store - enter "sort"
        To select top 5 products - enter "top"
        To start order processing - enter "order"
        To quit the app - enter "quit"
        """;
    System.out.println(commandsString);
    System.out.println("Enter command: ");

    Scanner scanner = new Scanner(System.in);
    while (true) {
      String command = scanner.next();
      runner.run(command);
      System.out.println(commandsString);
      System.out.println("Enter command: ");
    }
  }

}
