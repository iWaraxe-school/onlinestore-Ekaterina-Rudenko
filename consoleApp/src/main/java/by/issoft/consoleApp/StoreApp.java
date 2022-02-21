package by.issoft.consoleApp;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.Map;

public class StoreApp {
    public static void main(String[] args) {
        Store store = new Store();
        Map<Category, Integer> categoryIntegerMap = store.fillCategoriesWithProducts();
        for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
            System.out.println("\nCategory: " + entry.getKey().getName() +
                    ", quantity: " + entry.getValue().toString()
                    + "\n--------------------------------"
                    + "\n\t\tNAME\t RATE\t PRICE\t"
                    + "\n--------------------------------");
            for (Product product : entry.getKey().getProductList()) {
                System.out.println(product);
            }
        }
    }
}
