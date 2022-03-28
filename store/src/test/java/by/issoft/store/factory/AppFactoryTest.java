package by.issoft.store.factory;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppFactoryTest {

  @Test
  public void getApplicationTest(){
    Application actual = AppFactory.getApplication(AppType.CONSOLE);
    assertEquals(actual.getClass(), ConsoleApp.class);
  }
  @Test
  public void getApplicationDatabaseTest(){
    Application actual = AppFactory.getApplication(AppType.DATABASE);
    assertEquals(actual.getClass(), DatabaseApp.class);
  }
  @Test
  public void getApplicationHttpTest(){
    Application actual = AppFactory.getApplication(AppType.HTTP);
    assertEquals(actual.getClass(), HttpApp.class);
  }

}