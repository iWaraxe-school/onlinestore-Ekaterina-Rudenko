package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Store{

    private static Store instance;
    List<Category> categoryList;

    private Store() {
        this.categoryList = new ArrayList<>();
    }

    public static Store getInstance(){
        if(instance == null){
            instance = new Store();
        }
        return instance;
    }
    public List<Category> getCategoryList(){
        return categoryList;
    }

    public <T extends Category> void setCategoryList(T category) {
        categoryList.add(category);
    }

    public void showStoreInfo() {
        for (Category category : this.categoryList) {
            System.out.println("\nCategory: " + category.getName() +
                    ", quantity: " + category.getProductList().size()
                    + "\n--------------------------------"
                    + "\n\t\tNAME\t RATE\t PRICE\t"
                    + "\n--------------------------------");
            for (Product product : category.getProductList()) {
                System.out.print(product);
            }
        }
    }

    public List<Product> getWholeProductList() {
        return this.categoryList.stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
