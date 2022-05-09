package by.issoft.store.http;

import static io.restassured.RestAssured.given;

import by.issoft.domain.Category;
import by.issoft.store.Store;
import by.issoft.store.factory.HttpApp;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.NoHttpResponseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpOrderHandlerTest {
  Server server;
  Store store;
  HttpApp httpApp;

  @BeforeTest
  public void bind() {
    server = new Server();
    server.createBinding();
    store = Store.getInstance();
    store.getCategoryList().add(new Category("testCategory"));
    httpApp = new HttpApp();
    httpApp.fillStoreWithProducts(store);
  }

  @Test
  public void addCategoryTest() {
    RequestSpecification requestSpecification = given()
        .auth()
        .basic("username", "password");
    Response response = requestSpecification.when().post(Server.ORDER_PRODUCT_URL + "?number=3");
    int statusCode = response.getStatusCode();
    response.then().log().body();
    Assert.assertEquals(statusCode, 200);
  }
  @Test(expectedExceptions = NoHttpResponseException.class)
  public void addOrderErrorTest() {
    RequestSpecification requestSpecification = given()
        .auth()
        .basic("username", "password");
    Response response = requestSpecification.when().post(Server.ORDER_PRODUCT_URL + "wrongRequest");
  }

}
