package by.issoft.store.http;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HttpProductsHandlerTest {
  Server server;
  @BeforeMethod
  public void bind() {
    server = new Server();
    server.createBinding();
  }
  @Test
  public void showProductsTest() {
    RequestSpecification requestSpecification = given()
        .auth()
        .basic("username", "password");
    Response response = requestSpecification.when().get(Server.PRODUCTS_URL);
    response.then().log().body();
    int statusCode = response.getStatusCode();
    Assert.assertEquals(statusCode, 200);
  }
}
