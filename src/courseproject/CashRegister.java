package courseproject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CashRegister implements Runnable {
    private Cashier cashier;
    private Store store;
    private List<Goods> soldGoods;

    public CashRegister(Cashier cashier, Store store) {
        this.cashier = cashier;
        this.store = store;
        soldGoods = new ArrayList<>();
    }

    public void checkCustomer(Customer c){
            ConcurrentHashMap<Goods,Integer> shoppingList = c.getGoodsToBuy();
            for (Goods g:shoppingList.keySet()
            ) {
                int quantity = shoppingList.get(g);
                try {
                    store.sellGoods(g, quantity);
                    for (int i = 0; i < quantity; i++) {
                        soldGoods.add(g);
                    }
                }catch (OutOfStockException e){
                    System.out.println(e);
                }
            }
            issueReceipt(new Receipt(cashier,soldGoods));
            soldGoods.clear();
        }

    public void issueReceipt(Receipt r){
        String filepath = "receipt-" + Integer.toString(r.getId()) + ".txt";
        try(FileWriter fw = new FileWriter(filepath)) {
            System.out.println(r.toString());
            fw.write(r.toString());
            store.setNumberOfReceipt(store.getNumberOfReceipt()+1);
        } catch (IOException e) {
            System.out.println("IO Error: " + e);
        }
    }

    @Override
    public void run() {
        ConcurrentLinkedQueue<Customer> custs = store.getCustomers();
        while(!custs.isEmpty()) {
            System.out.println(Thread.currentThread().getName() + " is currently with a customer.");
            checkCustomer(custs.poll());
        }
    }
}
