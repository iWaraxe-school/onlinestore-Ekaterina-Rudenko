package by.issoft.store.sorting;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public static final String TEXT_VALIDATION = "(asc|desc)";
    private final XMLInputFactory inputFactory;
    Map<String, String> typeOrderMap;

    public XMLParser() {
        typeOrderMap = new LinkedHashMap<>();
        this.inputFactory = XMLInputFactory.newInstance();
    }

    public Map<String, String> getTypeOrderMap() {
        return typeOrderMap;
    }

    public void parseXml(String filePath) {
        System.out.println("Parsing is started...");
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(XMLTag.SORT.toString())) {
                        typeOrderMap = buildMap(reader);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error in StAX parser, XML file wasn't found " + "\n" + e);
        } catch (XMLStreamException e) {
            System.out.println("Error while parsing " + "\n" + e);
        } catch (IOException e) {
            System.out.println("Error in StAX parser while reading XML file " + filePath + "\n" + e);
        }
    }

    private Map<String, String> buildMap(XMLStreamReader reader) throws XMLStreamException {
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    String value = getXMLText(reader);
                    validateText(value);
                    typeOrderMap.put(name, value);
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    if (name.equals(XMLTag.SORT.toString())) {
                        System.out.println(typeOrderMap.entrySet());
                        return typeOrderMap;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <sort>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private void validateText(String value) throws XMLStreamException {
        if (!value.matches(TEXT_VALIDATION)) {
            throw new XMLStreamException("Invalid text");
        }
    }
}