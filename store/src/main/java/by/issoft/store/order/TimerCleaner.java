package by.issoft.store.order;

import by.issoft.domain.Product;
import by.issoft.store.handler.ProcessOrder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimerCleaner {
  final int delay;
  final int period;
 public TimerCleaner(int delay, int period){
   this.delay = delay;
   this.period = period;
 }

  public void cleanPurchasedGoods() {
    Timer timer = new Timer(true);
    timer.schedule(new Cleaner(), delay, period );
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
