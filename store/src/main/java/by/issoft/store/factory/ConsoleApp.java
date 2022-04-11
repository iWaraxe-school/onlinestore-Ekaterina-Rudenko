package by.issoft.store.factory;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.handler.Runner;
import by.issoft.store.helper.RandomStorePopulator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleApp implements Application {

    @Override
    public void fillStoreWithProducts(Store store) {
        Map<Category, Integer> categoryIntegerMap = store.getCategoryIntegerMap();
        for (Map.Entry<Category, Integer> entry : categoryIntegerMap.entrySet()) {
            List<Product> productsToAdd = RandomStorePopulator.generateProductList(entry.getValue());
            entry.getKey().addAllProducts(productsToAdd);
        }
    }

    @Override
    public void defineAppInterface() {
        System.out.println("Running console store application...");
        Runner runner = new Runner();
        System.out.println("\n\nTo sort the store - enter \"sort\"");
        System.out.println("To select top 5 products - enter \"top\"");
        System.out.println("To start order processing - enter \"order\"");
        System.out.println("To quit the app - enter \"quit\"");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter command: ");
            String command = scanner.next();
            runner.run(command);
        }
    }

}
