package by.issoft.store.sorting.comparator;

import by.issoft.domain.Product;

import java.util.Comparator;

public class RateComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return Double.compare(o1.getRate(), o2.getRate());
    }
}
