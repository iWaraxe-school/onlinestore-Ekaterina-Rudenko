package by.issoft.domain;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    public final double MIN_RATE = 0;
    public final double MAX_RATE = 5;
    private final String name;
    private double rate;
    private BigDecimal price;

    public Product(String name, double rate, BigDecimal price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
    }

    public Product(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        Preconditions.checkArgument(rate >= MIN_RATE && rate <= MAX_RATE, "Invalid rate value");
        this.rate = rate;
    }

    public void setPrice(BigDecimal price) {
        Preconditions.checkArgument(price.doubleValue() > 0, "Price must be positive value");
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.rate, rate) == 0 && Objects.equals(name, product.name) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, price);
    }

    @Override
    public String toString() {
        return String.format("%11s \t %3.2f \t %6.2f", name, rate, price);
    }
}
