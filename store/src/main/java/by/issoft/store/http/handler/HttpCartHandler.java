package by.issoft.store.http.handler;

import by.issoft.domain.Product;
import by.issoft.store.handler.ProcessOrder;
import by.issoft.store.http.HttpBasicHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.util.List;

public class HttpCartHandler extends HttpBasicHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    if("GET".equals(exchange.getRequestMethod())){
    List<Product> purchasedGoods  = ProcessOrder.getPurchasedProducts();
    Object responseObject = purchasedGoods;
    if(purchasedGoods.isEmpty()){
      responseObject = "The cart is empty.";
    }
    super.handle(exchange, responseObject);
    }else {
      byte[] errorResponse = "ErrorInCartHandler".getBytes();
      exchange.sendResponseHeaders(400, errorResponse.length);
      exchange.getResponseBody()
          .write(errorResponse);
    }
  }
}
