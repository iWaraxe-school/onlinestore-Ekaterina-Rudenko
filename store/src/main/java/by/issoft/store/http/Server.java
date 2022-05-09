package by.issoft.store.http;

import by.issoft.store.http.handler.HttpCartHandler;
import by.issoft.store.http.handler.HttpCategoryHandler;
import by.issoft.store.http.handler.HttpOrderHandler;
import by.issoft.store.http.handler.HttpProductsHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

  public static final String HOST = "localhost";
  public static final int PORT = 8080;

  public static final String URL = String.format("http://localhost:%s", PORT);
  public static final String ADD_CATEGORY_URL = String.format("%s/add_category", URL);
  public static final String PRODUCTS_URL = String.format("%s/show_products", URL);
  public static final String SHOW_CART_URL = String.format("%s/show_cart", URL);
  public static final String ORDER_PRODUCT_URL = String.format("%s/add_to_cart", URL);

  public void createBinding() {
    try {
      InetSocketAddress inetSocketAddress = new InetSocketAddress(HOST, PORT);
      HttpServer server = HttpServer.create(inetSocketAddress, 0);
      createContext(server, "/add_category", new HttpCategoryHandler());//POST, take category, generate products for this category
      createContext(server, "/show_products", new HttpProductsHandler());//GET, Show all the products
      createContext(server, "/add_to_cart", new HttpOrderHandler());//POST, add prod to cart
      createContext(server, "/show_cart", new HttpCartHandler());//GET, show products in the cart

      server.start();
      System.out.println("Server started: " + server.getAddress());
    } catch (IOException e){
      System.out.println("Server failed to start");
      e.printStackTrace();
    }
  }

  private void createContext(HttpServer server, String contextPath, HttpHandler handler) {
    server.createContext(contextPath, handler).setAuthenticator(new HttpAuthenticator("realm"));

  }
}
