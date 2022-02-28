package by.issoft.store.sorting.comparator;

import by.issoft.domain.Product;

import java.math.BigDecimal;
import java.util.Comparator;

public class PriceComparator implements Comparator<Product> {
    public int compare(Product prod1, Product prod2) {
        BigDecimal price1 = prod1.getPrice();
        BigDecimal price2 = prod2.getPrice();
        return price1.compareTo(price2);
    }
}
