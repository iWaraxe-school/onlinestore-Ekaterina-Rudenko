package by.issoft.store.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.handler.AbstractHandler;
import by.issoft.store.sorting.StoreComparator;

import java.util.List;

public class TopStore extends AbstractHandler {
    public TopStore(AbstractHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handleRequest(String request) {
        if (request.equals("top")) {
            StoreComparator filter = new StoreComparator();
            Store store = Store.getInstance();
            List<Product> productList = store.getWholeProductList();
            filter.topProducts(productList);
        } else super.handleRequest(request);
    }
}
