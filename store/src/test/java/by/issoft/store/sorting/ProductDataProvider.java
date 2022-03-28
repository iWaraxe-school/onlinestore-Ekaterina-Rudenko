package by.issoft.store.sorting;

import by.issoft.domain.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

  @DataProvider(name = "products")
  public Object[][] productData() {
    List<Product> productList = new ArrayList<>();
    productList.add(new Product("superBike", 3.6, new BigDecimal("123.1")));
    productList.add(new Product("superBike", 3.6, new BigDecimal("123.1")));
    productList.add(new Product("nokia", 4.7, new BigDecimal("100.1")));
    productList.add(new Product("nokia", 4.4, new BigDecimal("34.1")));
    productList.add(new Product("nokia1", 4.7, new BigDecimal("99.9")));
    productList.add(new Product("nokia2", 3.2, new BigDecimal("100.1")));
    List<Integer> expected = new ArrayList<>();
    expected.add(0);
    expected.add(1);
    expected.add(-1);
    return new Object[][]{{productList, expected}};
  }


  @DataProvider(name = "productsToSort")
  public Object[][] productDataToSort() {
    List<Product> productList = new ArrayList<>();
    productList.add(new Product("superBike", 3.6, new BigDecimal("123.1")));//0
    productList.add(new Product("nokia", 4.7, new BigDecimal("100.1")));//1
    productList.add(new Product("nokia", 4.4, new BigDecimal("34.1")));//2
    productList.add(new Product("nokia1", 4.7, new BigDecimal("99.9")));//3
    productList.add(new Product("nokia2", 3.2, new BigDecimal("100.1")));//4
    productList.add(new Product("milkshake", 4.9, new BigDecimal("11.1")));//5
    productList.add(new Product("yogurt", 3.4, new BigDecimal("12.8")));//6
    productList.add(new Product("cheese", 4.2, new BigDecimal("20.3")));//7
    productList.add(new Product("creamcheese", 4.12, new BigDecimal("18.2")));//8
    productList.add(new Product("milk", 3.9, new BigDecimal("5.1")));//9
    List<Product> expected = new ArrayList<>();
    expected.add(productList.get(7));
    expected.add(productList.get(8));
    expected.add(productList.get(9));
    expected.add(productList.get(5));
    expected.add(productList.get(1));
    expected.add(productList.get(2));
    expected.add(productList.get(3));
    expected.add(productList.get(4));
    expected.add(productList.get(0));
    expected.add(productList.get(6));
    return new Object[][]{{productList, expected}};
  }
}
