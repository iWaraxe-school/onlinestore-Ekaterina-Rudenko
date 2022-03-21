package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.handler.Runner;

import java.util.Scanner;

public class StoreApp {
    public static void main(String[] args) {
        Store store = Store.getInstance();
        store.showStoreInfo();
        Runner runner = new Runner();
        System.out.println("\n\nTo sort the store - enter \"sort\"");
        System.out.println("To select top 5 products - enter \"top\"");
        System.out.println("To quit the app - enter \"quit\"");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Enter command: ");
            String command = scanner.next();
            runner.run(command);
        }
    }
}
