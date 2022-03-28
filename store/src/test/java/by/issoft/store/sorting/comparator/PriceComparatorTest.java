package by.issoft.store.sorting.comparator;

import static org.testng.Assert.*;

import by.issoft.domain.Product;
import by.issoft.store.sorting.ProductDataProvider;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PriceComparatorTest {
  PriceComparator comparator;
  @BeforeTest
  public void setUp(){
    comparator = new PriceComparator();
  }
  @Test(dataProvider = "products", dataProviderClass = ProductDataProvider.class)
  public void comparePriceTest(List<Product> productList, List<Integer> expected){
    int actual = comparator.compare(productList.get(2), productList.get(5));
    Assert.assertEquals(actual, (int) expected.get(0));
  }
  @Test(dataProvider = "products", dataProviderClass = ProductDataProvider.class)
  public void comparePriceNegativeTest(List<Product> productList, List<Integer> expected){
    int actual = comparator.compare(productList.get(2), productList.get(3));
    Assert.assertEquals(actual, (int) expected.get(1));
  }

}