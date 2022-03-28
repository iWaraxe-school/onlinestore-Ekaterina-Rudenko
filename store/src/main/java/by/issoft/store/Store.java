package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helper.StoreHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Store{

    private static Store instance;
    Map<Category, Integer> categoryIntegerMap;

    private Store() {
        StoreHelper storeHelper = new StoreHelper();
        this.categoryIntegerMap = storeHelper.defineCategoriesAndQuantities();
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
    public Map<Category, Integer> getCategoryIntegerMap(){
        return categoryIntegerMap;
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

    public List<Product> getWholeProductList() {
        return this.categoryIntegerMap.keySet().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
