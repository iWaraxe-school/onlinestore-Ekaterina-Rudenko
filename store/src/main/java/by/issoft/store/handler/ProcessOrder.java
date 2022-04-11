package by.issoft.store.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.order.CommonResource;
import by.issoft.store.order.Order;
import by.issoft.store.order.TimerCleaner;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;


public class ProcessOrder extends AbstractHandler {

  static CopyOnWriteArrayList<Product> purchasedProducts;
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
      purchasedProducts = new CopyOnWriteArrayList<>();
      productList = store.getWholeProductList();
      commonResource = new CommonResource(productList);
      System.out.println("Started processing orders ...");

      final TimerCleaner cleaner = new TimerCleaner();
      cleaner.cleanPurchasedGoods();

      int threadsSize = 50;
      ExecutorService executorService = Executors.newFixedThreadPool(5);
      while(threadsSize > 0){
        executorService.submit(new Order(commonResource));
        threadsSize--;
      }

    } else {
      super.handleRequest(request);
    }
  }
}
