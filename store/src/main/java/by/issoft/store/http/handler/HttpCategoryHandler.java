package by.issoft.store.http.handler;

import by.issoft.domain.Category;
import by.issoft.store.Store;
import by.issoft.store.factory.HttpApp;
import by.issoft.store.http.HttpBasicHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;

public class HttpCategoryHandler extends HttpBasicHandler {

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      URI uri = exchange.getRequestURI();
      String categoryName = HttpBasicHandler.parseQuery(uri);
      System.out.println("Category name: " + categoryName);
      Object responseObject;
      Store store = Store.getInstance();
      List<String> names = store.getCategoryList().stream().map(Category::getName).toList();
      if (names.contains(categoryName)) {
        responseObject = "Category " + categoryName + " is already exists";
      } else {
        store.setCategoryList(new Category(categoryName));
        HttpApp httpApp = new HttpApp();
        httpApp.fillStoreWithProducts(store);
        responseObject = "Category  " + categoryName
            + " was added to store. Products for this category were generated.";
      }
      super.handle(exchange, responseObject);
    } catch (IOException e){
      byte[] errorResponse = "Error in adding category".getBytes();
      exchange.sendResponseHeaders(400, errorResponse.length);
      exchange.getResponseBody()
          .write(errorResponse);
    }
  }
}
