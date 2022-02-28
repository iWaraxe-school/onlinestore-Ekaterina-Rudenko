package by.issoft.store.sorting;

import by.issoft.domain.Product;
import by.issoft.store.sorting.comparator.NameComparator;
import by.issoft.store.sorting.comparator.PriceComparator;
import by.issoft.store.sorting.comparator.RateComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StoreFilter {
    public static final String ASCENDING = "asc";
    public static final String DESCENDING = "desc";
    public static final int TOP_FIVE = 5;

    public void sortProducts(List<Product> products, Map<String, String> typeOrderMap) {
        System.out.println("Started sorting...");
        List<Product> productList = new ArrayList<>(products);
        for (String type : typeOrderMap.keySet()) {
            String key = typeOrderMap.get(type);
            switch (XMLTag.valueOf(type.toUpperCase())) {
                case NAME -> {
                    if (key.equals(ASCENDING)) {
                        productList.sort(new NameComparator());
                        System.out.println("by name asc: ");
                        System.out.println(productList);
                    } else if (key.equals(DESCENDING)) {
                        productList.sort(new NameComparator().reversed());
                        System.out.println("by name desc: ");
                        System.out.println(productList);
                    }
                }
                case RATE -> {
                    if (key.equals(ASCENDING)) {
                        productList.sort(new RateComparator());
                        System.out.println("by rate asc: ");
                        System.out.println(productList);
                    } else if (key.equals(DESCENDING)) {
                        productList.sort(new RateComparator().reversed());
                        System.out.println("by rate desc: ");
                        System.out.println(productList);
                    }
                }
                case PRICE -> {
                    if (key.equals(ASCENDING)) {
                        productList.sort(new PriceComparator());
                        System.out.println("by PRICE asc: ");
                        System.out.println(productList);
                    } else if (key.equals(DESCENDING)) {
                        productList.sort(new PriceComparator().reversed());
                        System.out.println("by PRICE desc: ");
                        System.out.println(productList);
                    }
                }
            }
        }
    }

    public void topProducts(List<Product> products) {
        System.out.println("Selecting top 5 by price...");
        List<Product> productList = new ArrayList<>(products);
        List<Product> result = productList.stream()
                .sorted(new PriceComparator())
                .limit(TOP_FIVE)
                .collect(Collectors.toList());
        System.out.println(result);
    }
}
