package courseproject;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void readReceipt(int i){
        String filepath = "receipt-" + Integer.toString(i) + ".txt";
        try(FileReader fr = new FileReader(new File(filepath))) {
            BufferedReader br = new BufferedReader(fr);
            String s;
            while((s = br.readLine()) != null){
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }
    public static void main(String[] args) {
        Goods beans = new Goods("Beans", 2.31, 10);
        Goods greens = new Goods("Greens", 0.74, 5);
        Goods tomatoes = new Goods("Tomato", 3.21, 3);
        Goods potatoes = new Goods("Potatoes", 0.99, 17);

        Store supermarket = new Store("Supermarket");
        supermarket.stockGoods(beans, 5);
        supermarket.stockGoods(greens, 10);
        supermarket.stockGoods(tomatoes, 6);
        supermarket.stockGoods(potatoes,4);

        Cashier cashier1 = new Cashier("Cashier 1");
        Cashier cashier2 = new Cashier("Cashier 2");

        supermarket.addCashier(cashier1);
        supermarket.addCashier(cashier2);

        Customer c1 = new Customer(supermarket);
        Customer c2 = new Customer(supermarket);
        Customer c3 = new Customer(supermarket);

        c1.addToShoppingCart(beans,2);
        c1.addToShoppingCart(greens, 4);
        c2.addToShoppingCart(tomatoes,3);
        c2.addToShoppingCart(potatoes,1);
        c3.addToShoppingCart(potatoes,2);
        c3.addToShoppingCart(beans,1);
        c3.addToShoppingCart(tomatoes,3);

        supermarket.openStore();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        supermarket.closeStore();
//        Main.readReceipt(2);

    }
}
