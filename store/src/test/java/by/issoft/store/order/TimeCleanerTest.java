package by.issoft.store.order;

import by.issoft.domain.Product;
import by.issoft.store.handler.ProcessOrder;
import java.math.BigDecimal;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TimeCleanerTest {
  @Test
  public void timeCleanerTest() throws InterruptedException {
    ProcessOrder.getPurchasedProducts().add(new Product("testProduct", 4.3, new BigDecimal(12.3)));
    TimerCleaner cleaner = new TimerCleaner(1000, 4000);
    cleaner.cleanPurchasedGoods();
    Thread.sleep(1500);
    int size = ProcessOrder.getPurchasedProducts().size();
    Assert.assertEquals(size, 0);

  }

}
