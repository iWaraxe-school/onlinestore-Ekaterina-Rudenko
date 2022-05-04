package by.issoft.store.http.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.http.HttpBasicHandler;
import by.issoft.store.order.CommonResource;
import by.issoft.store.order.Order;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpOrderHandler extends HttpBasicHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      URI uri = exchange.getRequestURI();
      String productNumber = HttpBasicHandler.parseQuery(uri);
      System.out.println("Product number to order : " + productNumber);
      int number = Integer.parseInt(productNumber);
      Object responseObject;
      List<Product> productList = Store.getInstance().getWholeProductList();
      if (!productList.isEmpty()) {
        CommonResource commonResource = new CommonResource(productList);
        new Thread(new Order(commonResource, number)).start();
        responseObject = "Product  #" + number + " was added to cart.";
      } else {
        responseObject = "Store is empty yet. Please, add category first.";
      }
      super.handle(exchange, responseObject);
    }catch (IOException e){
      byte[] errorResponse = "Error in adding product to cart".getBytes();
      exchange.sendResponseHeaders(400, errorResponse.length);
      exchange.getResponseBody()
          .write(errorResponse);
    }
  }

}
