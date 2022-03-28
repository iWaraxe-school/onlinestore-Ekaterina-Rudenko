package by.issoft.store.helper;

import static org.testng.Assert.*;

import by.issoft.domain.Category;
import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoreHelperTest {

  StoreHelper storeHelper;
  @BeforeTest
  public void setUp(){
    storeHelper = new StoreHelper();
  }
  @Test
  public void defineCategoriesTest(){
    Map<Category, Integer> actualMap = storeHelper.defineCategoriesAndQuantities();
    List<String> actualNameList = actualMap.keySet().stream().map(Category::getName).toList();
    List<String> expectedNameList = new ArrayList<>();
    expectedNameList.add("Bike");
    expectedNameList.add("Phone");
    expectedNameList.add("Milk");
    Assert.assertTrue(actualNameList.size() == expectedNameList.size() &&
        actualNameList.containsAll(expectedNameList));
  }
}