package by.issoft.store.order;

import by.issoft.domain.Product;
import by.issoft.store.handler.ProcessOrder;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

public class Order extends Thread {
  CommonResource commonResource;

  public Order(CommonResource commonResource) {
    this.commonResource = commonResource;
  }

  @SneakyThrows
  @Override
  public void run() {
    int number = new Random().nextInt(0, commonResource.getListSize());
    Product product = commonResource.orderProduct(number);
    int random = new Random().nextInt(2, 4);
    System.out.println(
        "Order " + Thread.currentThread().getName() + " will be processing for " + random
            + " seconds");
    TimeUnit.SECONDS.sleep(random);
    ProcessOrder.getPurchasedProducts().add(product);
    System.out.println("Product " + product + " was added to purchased product list");
  }
}
