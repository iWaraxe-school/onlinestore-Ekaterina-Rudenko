package by.issoft.store.http.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.http.HttpBasicHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;

public class HttpProductsHandler extends HttpBasicHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException{
    if("GET".equals(exchange.getRequestMethod())) {
      List<Product> productList = Store.getInstance().getWholeProductList();
      super.handle(exchange, productList);
    } else {
      byte[] errorResponse = "ErrorInProductsHandler".getBytes();
      exchange.sendResponseHeaders(400, errorResponse.length);
      exchange.getResponseBody()
          .write(errorResponse);
    }
  }
}
