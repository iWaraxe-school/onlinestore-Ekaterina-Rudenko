package by.issoft.store.sorting;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.stream.XMLStreamException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class XMLParserTest {

  XMLParser parser;
  String PATH = "data/parsing_test.xml";
  String NO_FILE_PATH = "data/no_file.xml";

  @BeforeTest
  public void setUp() {
    parser = new XMLParser();
  }

  @Test
  public void parseXmlTest() {
    ClassLoader loader = getClass().getClassLoader();
    URL resource = loader.getResource(PATH);
    String filePath = new File(resource.getFile()).getPath();

    Map<String, String> actual = parser.parseXml(filePath);
    Map<String, String> expected = new HashMap<>();
    expected.put("name", "asc");
    expected.put("price", "asc");
    expected.put("rate", "desc");
    Assert.assertEquals(actual.keySet(), expected.keySet());
  }

  @Test
  public void parseXmlNegativeTest() {
    ClassLoader loader = getClass().getClassLoader();
    URL resource = loader.getResource(PATH);
    String filePath = new File(resource.getFile()).getPath();

    Map<String, String> actual = parser.parseXml(filePath);
    Map<String, String> expected = new HashMap<>();
    expected.put("name", "asc");
    expected.put("price", "asc");
    expected.put(" ", " ");
    Assert.assertNotEquals(actual, expected);
  }

  @Test(expectedExceptions = XMLStreamException.class)
  public void validateTextTest() throws XMLStreamException {
    parser.validateText("ASDE");
  }

  @Test
  public void validateTextPositiveTest() throws XMLStreamException {
    parser.validateText("asc");
  }

}