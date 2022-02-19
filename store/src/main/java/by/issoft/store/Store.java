package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helper.RandomStorePopulator;
import by.issoft.store.helper.StoreHelper;

import java.util.List;
import java.util.Map;

public class Store {

    public Map<Category, Integer> fillCategoriesWithProducts() {
        StoreHelper helper = new StoreHelper();
        Map<Category, Integer> categoryIntegerMap = helper.defineCategoriesAndQuantities();
        for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
            List<Product> productsToAdd = RandomStorePopulator.generateProductList(entry.getValue());
            entry.getKey().addAllProducts(productsToAdd);
        }
        return categoryIntegerMap;
    }
}
