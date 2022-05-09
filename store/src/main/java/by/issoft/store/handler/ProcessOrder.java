package by.issoft.store.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.order.CommonResource;
import by.issoft.store.order.Order;
import by.issoft.store.order.TimerCleaner;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.SneakyThrows;


public class ProcessOrder extends AbstractHandler {

  static CopyOnWriteArrayList<Product> purchasedProducts = new CopyOnWriteArrayList<>();
  List<Product> productList;
  CommonResource commonResource;

  public ProcessOrder(AbstractHandler nextHandler) {
    super(nextHandler);
  }

  public static CopyOnWriteArrayList<Product> getPurchasedProducts() {
    return purchasedProducts;
  }


  @SneakyThrows
  @Override
  public void handleRequest(String request) {
    if (request.equals("order")) {
      Store store = Store.getInstance();
      productList = store.getWholeProductList();
      commonResource = new CommonResource(productList);
      System.out.println("Started processing orders ...");

      final TimerCleaner cleaner = new TimerCleaner(20000, 30000);
      cleaner.cleanPurchasedGoods();

   /*   int threadsSize = 3;
      ExecutorService executorService = Executors.newFixedThreadPool(5);
      while(threadsSize > 0){
        executorService.submit(new Order(commonResource));
        threadsSize--;
      }*/
      System.out.println("Enter the number product between 1 and " + productList.size());
      Scanner scanner = new Scanner(System.in);
      int number = scanner.nextInt();
      new Thread(new Order(commonResource, number)).start();
    } else {
      super.handleRequest(request);
    }
  }
}
