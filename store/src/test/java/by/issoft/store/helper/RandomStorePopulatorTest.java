package by.issoft.store.helper;

import static org.testng.Assert.*;

import by.issoft.domain.Product;
import java.util.List;
import org.testng.annotations.Test;

public class RandomStorePopulatorTest {
  @Test
  public void generateProductListTest(){
    List<Product> actual = RandomStorePopulator.generateProductList(10);
    assertEquals(actual.size(), 10);
  }

}