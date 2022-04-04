package by.issoft.store.order;

import static org.testng.Assert.*;

import by.issoft.domain.Product;
import by.issoft.store.sorting.ProductDataProvider;
import java.math.BigDecimal;
import java.util.List;
import org.testng.annotations.Test;

public class CommonResourceTest {
  CommonResource commonResource;

  @Test(dataProvider = "productsToSort", dataProviderClass = ProductDataProvider.class)
  public void orderProductTest(List<Product> productList,  List<Product> expected){
    commonResource = new CommonResource(productList);
    Product actual = commonResource.orderProduct(0);
    Product expectedProduct = new Product("superBike", 3.6, new BigDecimal("123.1"));
    assertEquals(actual, expectedProduct);
  }

  @Test(dataProvider = "productsToSort", dataProviderClass = ProductDataProvider.class)
  public void checkSizeTest(List<Product> productList,  List<Product> expected){
    commonResource = new CommonResource(productList);
    int actualSize = commonResource.getListSize();
    assertEquals(actualSize, 10);
  }

}