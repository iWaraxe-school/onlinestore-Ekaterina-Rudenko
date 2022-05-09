package by.issoft.store.handler;

import by.issoft.store.Store;
import by.issoft.store.factory.ConsoleApp;
import org.testng.annotations.Test;

public class HandlerTest {
  @Test
  public void handlerTest(){
    Store store = Store.getInstance();
    ConsoleApp consoleApp = new ConsoleApp();
    consoleApp.fillStoreWithProducts(store);
    Runner runner = new Runner();
    runner.run("top");
  }

}
