package by.issoft.store.order;

import by.issoft.domain.Product;
import java.util.List;

public class CommonResource {
  List<Product> productList;
  public CommonResource (List<Product> productList){
    this.productList = productList;
  }
  public synchronized Product orderProduct(int i){
    Product product = productList.get(i);
    productList.remove(i);
    return product;
  }
  public int getListSize(){
    return productList.size();
  }

}
