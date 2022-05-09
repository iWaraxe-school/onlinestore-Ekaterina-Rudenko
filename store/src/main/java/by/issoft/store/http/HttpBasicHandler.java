package by.issoft.store.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class HttpBasicHandler implements HttpHandler {
  private static final String EQUAL_DELIMITER = "=";

  public void handle(HttpExchange exchange, Object object) throws IOException {
    String response = object.toString();
    OutputStream outputStream = exchange.getResponseBody();
    exchange.sendResponseHeaders(200, response.length());
    outputStream.write(response.getBytes());
    outputStream.flush();
    outputStream.close();
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException{
    throw new UnsupportedOperationException("No such operation");
  }

  public static String parseQuery(URI uri) {
    String query = uri.getQuery();
    String[] array = query.trim().split(EQUAL_DELIMITER);
    return array[1];
  }
}
