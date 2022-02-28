package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.sorting.StoreFilter;
import by.issoft.store.sorting.XMLParser;

import java.util.ArrayList;
import java.util.List;

public class StoreCommandHandler {
    public static final String PATH = "store/src/main/resources/config.xml";
    public void handleCommand(String input, Store store) {
        StoreFilter filter = new StoreFilter();

        List<Product> productList = getWholeProductList(store);
        switch (input) {
            case "top" -> {
                filter.topProducts(productList);
            }
            case "sort" -> {
                XMLParser parser = new XMLParser();
                parser.parseXml(PATH);
                filter.sortProducts(productList, parser.getTypeOrderMap());
            }
            case "quit" -> {
                System.exit(0);
            }
        }
    }

    private List<Product> getWholeProductList(Store store) {
        List<Product> productList = new ArrayList<>();
        for (Category category : store.categoryIntegerMap.keySet()) {
            List<Product> productsToAdd = category.getProductList();
            productList.addAll(productsToAdd);
        }
        return productList;
    }
}