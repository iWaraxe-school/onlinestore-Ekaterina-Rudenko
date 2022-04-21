package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {

  private String name;
  private List<Product> productList;

  public Category(String name) {
    this.name = name;
    this.productList = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void addProduct(Product product) {
        this.productList.add(product);
  }

  public void addAllProducts(List<Product> productList) {
    if (productList != null) {
      this.productList.addAll(productList);
    }
  }

  @Override
  public String toString() {
    return "\nCategory " + "name='" + name + "\n" +
        productList;
  }
}
