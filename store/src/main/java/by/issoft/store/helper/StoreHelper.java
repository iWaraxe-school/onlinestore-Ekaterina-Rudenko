package by.issoft.store.helper;

import by.issoft.domain.Category;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StoreHelper {
    public static final String PACKAGE = "by.issoft.domain.categories";
    public static final int MAX_QUANTITY = 50;

    public Map<Category, Integer> defineCategoriesAndQuantities() {
        Map<Category, Integer> categories = new HashMap<>();
        Set<Class<? extends Category>> categorySubtypes = getCategorySubtypes();
        System.out.println("The following categories and numbers of products have been defined: ");
        int counter = 0;
        for (Class<? extends Category> subtype : categorySubtypes) {
            try {
                Category subCategory = subtype.getConstructor().newInstance();
                Random random = new Random();
                Integer quantity = random.nextInt(MAX_QUANTITY);
                categories.put(subCategory, quantity);
                System.out.println( ++counter + ". category name: " + subCategory.getName() + ", quantity: " + quantity);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.out.println("Failed to define subcategories" + e);
            }
        }
        System.out.println();
        return categories;
    }

    private Set<Class<? extends Category>> getCategorySubtypes() {
        Reflections reflections = new Reflections(PACKAGE, new SubTypesScanner());
        return reflections.getSubTypesOf(Category.class);
    }


}
