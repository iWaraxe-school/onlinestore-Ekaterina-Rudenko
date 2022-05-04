package by.issoft.store.http;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HttpCategoryHandlerTest {

  Server server;

  @BeforeTest
  public void bind() {
    server = new Server();
    server.createBinding();
  }

  @Test
  public void addCategoryTest() {
    RequestSpecification requestSpecification = given()
        .auth()
        .basic("username", "password");
    Response response = requestSpecification.when().post(Server.ADD_CATEGORY_URL + "?name=bike");
    int statusCode = response.getStatusCode();
    response.then().log().body();
    Assert.assertEquals(statusCode, 200);
  }

  @Test
  public void addCategoryErrorTest() {
    RequestSpecification requestSpecification = given()
        .auth()
        .basic("username", "password");
    Response response = requestSpecification.when().post(Server.SHOW_CART_URL + "wrongRequest");
    int statusCode = response.getStatusCode();
    Assert.assertEquals(statusCode, 400);
  }
}
