package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helper.RandomStorePopulator;
import by.issoft.store.helper.StoreHelper;

import java.util.List;
import java.util.Map;

public class Store implements Cloneable{
    Map<Category, Integer> categoryIntegerMap;

    public Store() {
        this.categoryIntegerMap = fillCategoriesWithProducts();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Map<Category, Integer> getCategoryIntegerMap() {
        return categoryIntegerMap;
    }

    public void showShopInfo() {
        for (Map.Entry<Category, Integer> entry : this.categoryIntegerMap.entrySet()) {
            System.out.println("\nCategory: " + entry.getKey().getName() +
                    ", quantity: " + entry.getValue().toString()
                    + "\n--------------------------------"
                    + "\n\t\tNAME\t RATE\t PRICE\t"
                    + "\n--------------------------------");
            for (Product product : entry.getKey().getProductList()) {
                System.out.print(product);
            }
        }
    }

    private Map<Category, Integer> fillCategoriesWithProducts() {
        StoreHelper helper = new StoreHelper();
        Map<Category, Integer> categoryIntegerMap = helper.defineCategoriesAndQuantities();
        for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
            List<Product> productsToAdd = RandomStorePopulator.generateProductList(entry.getValue());
            entry.getKey().addAllProducts(productsToAdd);
        }
        return categoryIntegerMap;
    }
}
