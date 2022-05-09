package by.issoft.store.factory;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helper.RandomStorePopulator;
import by.issoft.store.http.HttpClient;
import by.issoft.store.http.Server;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HttpApp implements Application {

  @Override
  public void fillStoreWithProducts(Store store) {
    int size = store.getCategoryList().size();
    Category category = store.getCategoryList().get(size - 1);
    List<Product> productList = RandomStorePopulator.generateProductList(new Random().nextInt(30));
    category.addAllProducts(productList);
    System.out.println("Products for category " + category + " were generated and added to store");
  }

  @Override
  public void defineAppInterface() {
    Server server = new Server();
    server.createBinding();
    HttpClient httpClient = new HttpClient();
    String commands = ("""
        category - to add the category
        store - to show all the products
        order  - to add product to the cart
        cart - to show products in the cart
        exit - to exit the app""");
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println(commands);
      System.out.println("Enter command: ");
      String command = scanner.next();
      switch (command) {
        case ("category") -> {
          System.out.println("Enter categoty name: ");
          String category = scanner.next();
          httpClient.addCategory(category);}
        case ("store") ->
          httpClient.showProducts();
        case ("order") -> {
          int storeSize = Store.getInstance().getWholeProductList().size();
          if(storeSize == 0){
            System.out.println("The store is empty yet. Please, add category first.");
            break;
          }
          System.out.println("Enter product number from 0 to " + (storeSize-1));
          int number = scanner.nextInt();
          httpClient.makeOrder(String.valueOf(number));
        }
        case ("cart") ->
            httpClient.showCart();
        case ("exit") ->
            System.exit(0);
        default -> System.out.println("Not supported command");
      }
    }
  }
}
