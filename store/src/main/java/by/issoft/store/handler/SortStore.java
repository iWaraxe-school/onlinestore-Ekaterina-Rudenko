package by.issoft.store.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.sorting.StoreComparator;
import by.issoft.store.sorting.XMLParser;

import java.util.List;

public class SortStore extends AbstractHandler {
    public static final String PATH = "store/src/main/resources/config.xml";

    public SortStore(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String request) {
        if (request.equals("sort")) {
            XMLParser parser = new XMLParser();
            StoreComparator filter = new StoreComparator();
            Store store = Store.getInstance();
            List<Product> productList = store.getWholeProductList();
            parser.parseXml(PATH);
            System.out.println("Started sorting...");
            filter.sortProducts(productList, parser.getTypeOrderMap());
        } else super.handleRequest(request);
    }
}