package by.issoft.store.helper;

import by.issoft.domain.Category;
import java.util.stream.Stream;
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

    public  Map<Category, Integer> defineCategoriesAndQuantities() {
        Map<Category, Integer> categories = new HashMap<>();
        Random random = new Random();
        Set<Class<? extends Category>> categorySubtypes = getCategorySubtypes();
        System.out.println("The following categories and numbers of products have been defined: ");
        for (Class<? extends Category> subtype : categorySubtypes) {
            try {
                Category subCategory = subtype.getConstructor().newInstance();
                categories.put(subCategory, random.nextInt(MAX_QUANTITY));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                System.out.println("Failed to define subcategories" + e);
            }
        }
        Stream.of(categories).forEach(c -> c.entrySet()
            .forEach(e -> System.out.println("Category name: " + e.getKey().getName() + ", quantity: " + e.getValue())));
        return categories;
    }

    private Set<Class<? extends Category>> getCategorySubtypes() {
        Reflections reflections = new Reflections(PACKAGE, new SubTypesScanner());
        return reflections.getSubTypesOf(Category.class);
    }


}
