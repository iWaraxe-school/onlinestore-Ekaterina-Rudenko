package by.issoft.store.sorting;

import static org.testng.Assert.*;

import by.issoft.domain.Product;
import by.issoft.store.sorting.comparator.NameComparator;
import by.issoft.store.sorting.comparator.PriceComparator;
import by.issoft.store.sorting.comparator.RateComparator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoreComparatorTest {

  StoreComparator storeComparator;
  Map<String, String> map1;

  @BeforeTest
  public void setUp() {
    storeComparator = new StoreComparator();
    map1 = new HashMap<>();
    map1.put("name", "asc");
  }

  @Test
  public void chooseComparatorTest() {
    Comparator<Product> expected = storeComparator.chooseComparator(Map.entry("name", "asc"));
    assertEquals(NameComparator.class, expected.getClass());
  }
  @Test
  public void chooseComparatorPriceTest() {
    Comparator<Product> expected = storeComparator.chooseComparator(Map.entry("price", "asc"));
    assertEquals(PriceComparator.class, expected.getClass());
  }

  @Test
  public void chooseComparatorRateTest() {
    Comparator<Product> expected = storeComparator.chooseComparator(Map.entry("rate", "asc"));
    assertEquals(RateComparator.class, expected.getClass());
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void chooseComparatorExceptionTest() {
    storeComparator.chooseComparator(Map.entry("nameWrong", "asc"));
  }

  @Test(dataProvider = "productsToSort", dataProviderClass = ProductDataProvider.class)
  public void sortProductsTest(List<Product> productList, List<Product> expected) {
    List<Product> actualList = storeComparator.sortProducts(productList, map1);
    Assert.assertEquals(actualList, expected);
  }

  @Test(dataProvider = "productsToSort", dataProviderClass = ProductDataProvider.class)
  public void topProductsTest(List<Product> productList, List<Product> expected) {
    List<Product> actualList = storeComparator.topProducts(productList);
    List<Product> expectedList = new ArrayList<>();
    expectedList.add(productList.get(0));
    expectedList.add(productList.get(1));
    expectedList.add(productList.get(4));
    expectedList.add(productList.get(3));
    expectedList.add(productList.get(2));
    Assert.assertEquals(actualList, expectedList);
  }


}