package by.issoft.store;

import static org.testng.Assert.*;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.factory.AppFactory;
import by.issoft.store.factory.AppType;
import by.issoft.store.factory.Application;
import by.issoft.store.helper.StoreHelper;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoreTest {

  Store store;
  Application application;

  @BeforeTest
  public void setUp() throws SQLException {
    store = Store.getInstance();
    application = AppFactory.getApplication(AppType.CONSOLE);
    application.fillStoreWithProducts(store);
  }

  @Test
  public void getWholeProductListTest() {
    List<Category> categoryList = store.getCategoryList();
    List<Product> actualProductList = store.getWholeProductList();
    int expextedSize = categoryList
        .stream()
        .mapToInt(i -> i.getProductList().size())
        .sum();
    assertEquals(actualProductList.size(), expextedSize);
  }

}