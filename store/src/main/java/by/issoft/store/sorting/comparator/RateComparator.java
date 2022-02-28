package by.issoft.store.sorting.comparator;

import by.issoft.domain.Product;
import java.util.Comparator;

public class RateComparator implements Comparator<Product> {
    public int compare(Product prod1, Product prod2) {
        double rate1 = prod1.getRate();
        double rate2 = prod2.getRate();
        return Double.compare(rate1, rate2);
    }
}
