package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helper.RandomStorePopulator;
import by.issoft.store.helper.StoreHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store{

    private static Store instance;
    Map<Category, Integer> categoryIntegerMap;

    private Store() {
        this.categoryIntegerMap = fillCategoriesWithProducts();
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }

    public void showStoreInfo() {
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

    public List<Product> getWholeProductList() {
        return this.categoryIntegerMap.keySet().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
