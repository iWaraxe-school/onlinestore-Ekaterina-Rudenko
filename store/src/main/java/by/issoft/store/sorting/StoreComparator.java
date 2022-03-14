package by.issoft.store.sorting;

import by.issoft.domain.Product;
import by.issoft.store.sorting.comparator.NameComparator;
import by.issoft.store.sorting.comparator.PriceComparator;
import by.issoft.store.sorting.comparator.RateComparator;

import java.util.*;
import java.util.stream.Collectors;

public class StoreComparator {
    public static final String ASCENDING = "asc";
    public static final int TOP_FIVE = 5;

    public void sortProducts(List<Product> products, Map<String, String> typeOrderMap) {
        List<Product> productList = new ArrayList<>(products);
        for (Map.Entry<String, String> entry : typeOrderMap.entrySet()) {
            productList.sort(chooseComparator(entry));
            System.out.println("by " + entry.getKey() + " " + entry.getValue() + ": \n" + productList);
        }
    }

    protected Comparator<Product> chooseComparator(Map.Entry<String, String> entry) {
        String field = entry.getKey();
        String value = entry.getValue();

        switch (XMLTag.valueOf(field.toUpperCase())) {
            case NAME -> {
                return checkValue(value) ? new NameComparator() : new NameComparator().reversed();
            }
            case RATE -> {
                return checkValue(value) ? new RateComparator() : new RateComparator().reversed();
            }
            case PRICE -> {
                return checkValue(value) ? new PriceComparator() : new PriceComparator().reversed();
            }
            default -> System.out.println("No such field in product");
        }
        throw new
                RuntimeException("Exception while choosing comparator");
    }

    public void topProducts(List<Product> products) {
        System.out.println("Selecting top 5 by price descending...");
        System.out.println(products.stream()
                .sorted(new PriceComparator().reversed())
                .limit(TOP_FIVE)
                .collect(Collectors.toList()));
    }

    protected boolean checkValue(String value) {
        //the text was validated in parser so no need to check for "descending" or other words
        return value.equals(ASCENDING);
    }
}
