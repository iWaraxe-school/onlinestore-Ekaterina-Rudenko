package by.issoft.store.http;

import com.sun.net.httpserver.BasicAuthenticator;

public class HttpAuthenticator extends BasicAuthenticator {

  public static final String USERNAME = "username";
  public static final String PASSWORD = "password";

  public HttpAuthenticator(String realm) {
    super(realm);
  }

  @Override
  public boolean checkCredentials(String username, String password) {
    return username.equals(USERNAME)&&password.equals(PASSWORD);
  }
}
