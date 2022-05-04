package by.issoft.store.http;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpClientTest {
  HttpClient client;
  Server server;

  @BeforeTest
  public void bind(){
    server = new Server();
    server.createBinding();
    client = new HttpClient();
  }
  @Test
  public void showProductsTest(){
    client.showProducts();
  }
  @Test
  public void showCartTest(){
    client.showCart();
  }
  @Test
  public void addCategoryTest(){
    String category = "bike";
    client.addCategory(category);
  }
  @Test
  public void addOrderTest(){
    String number = "3";
    client.addCategory("testCategory");
    client.makeOrder(number);
  }

}
