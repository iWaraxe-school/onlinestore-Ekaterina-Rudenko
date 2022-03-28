package by.issoft.store.sorting;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class XMLParser {
    public static final String TEXT_VALIDATION = "(asc|desc)";
    private final XMLInputFactory inputFactory;

    public XMLParser() {
        this.inputFactory = XMLInputFactory.newInstance();
    }

    public Map<String, String> parseXml(String filePath) {
        System.out.println("Parsing is started...");
        XMLStreamReader reader;
        String name;
        Map<String, String> typeOrderMap = new LinkedHashMap<>();
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(XMLTag.SORT.toString())) {
                        buildMap(reader, typeOrderMap);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            System.out.println("Error in StAX parser, XML file wasn't found or error while reading XML file " + filePath + "\n" + e);
        }
        return typeOrderMap;
    }

    private void buildMap(XMLStreamReader reader, Map<String, String> typeOrderMap) throws XMLStreamException {
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
                    }
                }
            }
        }
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    public void validateText(String value) throws XMLStreamException {
        if (!value.matches(TEXT_VALIDATION)) {
            throw new XMLStreamException("Invalid text");
        }
    }
}