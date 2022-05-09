package by.issoft.store.http;

import static io.restassured.RestAssured.given;

public class HttpClient {

  private static final String USERNAME = "username";
  private static final String PASSWORD = "password";

  public void showProducts() {
    given()
        .auth()
        .basic(USERNAME, PASSWORD)
        .when()
        .get(Server.PRODUCTS_URL)
        .then()
        .log().body();
  }

  public void showCart() {
    given()
        .auth()
        .basic(USERNAME, PASSWORD)
        .when()
        .get(Server.SHOW_CART_URL)
        .then()
        .log().body();
  }

  public void addCategory(String categoryName) {
    String request = "?name=" + categoryName;
    given()
        .auth()
        .basic(USERNAME, PASSWORD)
        .when()
        .post(Server.ADD_CATEGORY_URL + request)
        .then()
        .log().body();
  }

  public void makeOrder(String productNumber) {
    String request = "?number=" + productNumber;
    given()
        .auth()
        .basic(USERNAME, PASSWORD)
        .when()
        .post(Server.ORDER_PRODUCT_URL + request)
        .then()
        .log().body();
  }
}
