package by.issoft.store.order;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.factory.ConsoleApp;
import by.issoft.store.handler.ProcessOrder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import lombok.SneakyThrows;

public class Order extends Thread {

  CommonResource commonResource;

  int number;
  public Order(CommonResource commonResource, int number) {

    this.commonResource = commonResource;
    this.number = number;
  }

  @SneakyThrows
  @Override
  public void run() {
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
