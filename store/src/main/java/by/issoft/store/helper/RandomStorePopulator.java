package by.issoft.store.helper;

import by.issoft.domain.Product;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RandomStorePopulator {
    private static final String PRODUCT_NAME_REGEX = "[A-Z][a-z]{4,10}";
    private static final long MAX_PRICE = 1000;
    private static final long MIN_PRICE = 1;
    private static final int PRECISION = 2;
    private static final int MIN_RATE = 1;
    private static final int MAX_RATE = 5;

    public static List<Product> generateProductList(int quantity) {
        Faker faker = new Faker();
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i <= quantity; i++) {
            Product product = new Product(faker.regexify(PRODUCT_NAME_REGEX));
            product.setRate(faker.number().randomDouble(PRECISION, MIN_RATE, MAX_RATE));
            product.setPrice(BigDecimal.valueOf(faker.number().randomDouble(PRECISION, MIN_PRICE, MAX_PRICE)));
            productList.add(product);
        }
        return productList;
    }
}
