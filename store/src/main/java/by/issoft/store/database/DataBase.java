package by.issoft.store.database;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

  private static final String DB_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:~/test";
  private static final String DB_USER = "admin";
  private static final String DB_PASSWORD = "1234";

  private static final String CATEGORY_TABLE_CREATE_QUERY = """
      CREATE TABLE IF NOT EXISTS categories
      (id INT NOT NULL AUTO_INCREMENT,
      c_name VARCHAR(20) PRIMARY KEY NOT NULL)""";
  private static final String PRODUCTS_TABLE_CREATE_QUERY = """
      CREATE TABLE IF NOT EXISTS products
      (id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
      p_name VARCHAR(50) NOT NULL,
      p_rate DOUBLE NOT NULL,
      p_price DOUBLE NOT NULL,
      c_name VARCHAR NOT NULL,
      FOREIGN KEY (c_name)
      REFERENCES categories(c_name))""";

  private static final String CATEGORY_INSERT_QUERY = """
      INSERT INTO categories (c_name) VALUES(?)""";
  private static final String PRODUCT_INSERT_QUERY = """
      INSERT INTO products (p_name, p_rate, p_price, c_name) VALUES(?, ?, ?, ?)""";
  private static final String PRODUCT_SHOW_ALL_QUERY = """
      SELECT (p_name, p_rate, p_price) FROM products""";
  private static final String PRODUCT_SHOW_BY_CATEGORY_QUERY = """
      SELECT (p_name, p_rate, p_price) FROM products WHERE c_name = ? """;

  public Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName(DB_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
      System.out.println("Failed to get connection " + e);
    }
    return connection;
  }

  public void createDbTables() {
    try (Statement statement = getConnection().createStatement()) {
      statement.executeUpdate(CATEGORY_TABLE_CREATE_QUERY);
      statement.executeUpdate(PRODUCTS_TABLE_CREATE_QUERY);
    } catch (SQLException e) {
      System.out.println("Failed to create the tables");
      e.printStackTrace();
    }
  }

  public int insertCategory(Category category) {
    int categoryId = 0;
    Connection connection = getConnection();
    try (PreparedStatement statement = connection.prepareStatement(CATEGORY_INSERT_QUERY,
        Statement.RETURN_GENERATED_KEYS)) {
      statement.setString(1, category.getName());
      statement.executeUpdate();
      try (ResultSet resultSet = statement.getGeneratedKeys()) {
        if (resultSet.next()) {
          categoryId = resultSet.getInt(1);
          System.out.println(
              "Category " + category.getName() + " with id " + categoryId + " was added to db.");
        }
      }
    } catch (SQLException e) {
      System.out.println("Failed to insert category");
      e.printStackTrace();
    }
    return categoryId;
  }

  public boolean insertProduct(Product product, Category category) {
    boolean isAdded = false;
    Connection connection = getConnection();
    try (PreparedStatement statement = connection.prepareStatement(PRODUCT_INSERT_QUERY)) {
      statement.setString(1, product.getName());
      statement.setDouble(2, product.getRate());
      statement.setDouble(3, product.getPrice().doubleValue());
      statement.setString(4, category.getName());
      isAdded = (statement.executeUpdate() == 1);
    } catch (SQLException e) {
      System.out.println("Failed to add new product");
      e.printStackTrace();
    }
    return isAdded;
  }

  public List<Product> showStore() {
    Connection connection = getConnection();
    List<Product> productList = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(PRODUCT_SHOW_ALL_QUERY)) {
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Product product = new Product(resultSet.getString(1), resultSet.getDouble(2), BigDecimal.valueOf(resultSet.getDouble(3)));
        productList.add(product);
      }
    } catch (SQLException e) {
      System.out.println("Failed to find all products");
      e.printStackTrace();
    }
    return productList;
  }

  public List<Product> showStoreByCategory(String category) {
    Connection connection = getConnection();
    List<Product> productList = new ArrayList<>();
    try (PreparedStatement statement = connection.prepareStatement(
        PRODUCT_SHOW_BY_CATEGORY_QUERY)) {
      statement.setString(1, category);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        Product product = mapProduct(resultSet);
        productList.add(product);
      }
    } catch (SQLException e) {
      System.out.println("Failed to find products by category: " + category);
      e.printStackTrace();
    }
    return productList;
  }

  private Product mapProduct(ResultSet resultSet) throws SQLException {
    Product product = new Product();
    product.setName(resultSet.getString(1));
    product.setRate(resultSet.getDouble(2));
    product.setPrice(BigDecimal.valueOf(resultSet.getDouble(3)));
    return product;
  }


}
