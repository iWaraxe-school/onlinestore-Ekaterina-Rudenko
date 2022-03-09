package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.sorting.StoreComparator;
import by.issoft.store.sorting.XMLParser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StoreCommandHandler {
    public static final String PATH = "store/src/main/resources/config.xml";
    public void handleCommand(String input, Store store) {
        StoreComparator filter = new StoreComparator();

        List<Product> productList = getWholeProductList(store);
        switch (input) {
            case "top" ->
                filter.topProducts(productList);
            case "sort" -> {
                XMLParser parser = new XMLParser();
                parser.parseXml(PATH);
                System.out.println("Started sorting...");
                filter.sortProducts(productList, parser.getTypeOrderMap());
            }
            case "quit" -> System.exit(0);
        }
    }

    private List<Product> getWholeProductList(Store store) {
        return store.categoryIntegerMap.keySet().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}