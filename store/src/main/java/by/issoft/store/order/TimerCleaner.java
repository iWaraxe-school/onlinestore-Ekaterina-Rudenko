package by.issoft.store.order;

import by.issoft.domain.Product;
import by.issoft.store.handler.ProcessOrder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimerCleaner {


  public void cleanPurchasedGoods() {
    Timer timer = new Timer(true);
    timer.schedule(new Cleaner(), 5000, 10000 );
  }

  public static class Cleaner extends TimerTask {

    @Override
    public void run() {
      CopyOnWriteArrayList<Product> list = ProcessOrder.getPurchasedProducts();
        System.out.println("\nStarted clean goods: " + list);
        list.clear();
        System.out.println("Purchased products list has been cleaned\n");
    }
  }
}
