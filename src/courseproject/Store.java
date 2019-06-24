package courseproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Store {
    private String name;
    private List<Cashier> cashiers;
//    private List<Customer> customers;
    private ConcurrentLinkedQueue<Customer> customers;
    private ConcurrentHashMap<Integer, List<Goods>> goods;
    private int numberOfReceipt;
    private double income;

    public Store(String name) {
        this.name = name;
        cashiers = new ArrayList<>();
//        customers = new ArrayList<>();
        customers = new ConcurrentLinkedQueue<>();
        goods = new ConcurrentHashMap<>();
        numberOfReceipt = 0;
        income = 0;
    }

    public void stockGoods(Goods g, int quantity) {
        if (goods.containsKey(g.getId())) {
            List<Goods> goodsList = goods.get(g.getId());
            for (int i = 0; i < quantity; i++) {
                goodsList.add(g);
            }
        }
        List<Goods> newGoods = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            newGoods.add(g);
        }
        goods.put(g.getId(), newGoods);
    }

    public synchronized void sellGoods(Goods g, int quantity) throws OutOfStockException {
        List<Goods>instock = goods.get(g.getId());
        if(goods.containsKey(g.getId())){
            if(instock.size()<quantity){
                throw new OutOfStockException(quantity - instock.size(),g.getId());

            }
            for (int i = 0; i < quantity; i++) {
                instock.remove(g);
            }
            income += g.getPrice()*quantity;
        }
        else {
            throw new OutOfStockException(quantity - instock.size(),g.getId());

        }
    }

    public void addCashier(Cashier c){
        if (cashiers.contains(c)){
            System.out.println("Cashier already working");
        }
        cashiers.add(c);
    }

    public void addCustomers(Customer c){
        if (customers.contains(c)){
            System.out.println("Customer already in queue");
        }
        customers.add(c);
    }

    public void setNumberOfReceipt(int numberOfReceipt) {
        this.numberOfReceipt = numberOfReceipt;
    }

    public int getNumberOfReceipt() {
        return numberOfReceipt;
    }

//    public synchronized List<Customer> getCustomers() {
//        return customers;
//    }


    public ConcurrentLinkedQueue<Customer> getCustomers() {
        return customers;
    }


    public void openStore(){
        for (Cashier c:cashiers) {
            Thread cashRegThread = new Thread(new CashRegister(c,this));
            cashRegThread.setName(c.getName());
            cashRegThread.start();
        }
    }

    public void closeStore(){
        System.out.println("Total income for today: " + income);
        System.out.println("Number of issued receipts: " + numberOfReceipt);
    }

    @Override
    public String toString() {
        return "Store{" +
                "name='" + name + '\'' +
                ", cashiers=" + cashiers +
                ", customers=" + customers +
                ", goods=" + goods +
                ", numberOfReceipt=" + numberOfReceipt +
                ", income=" + income +
                '}';
    }
}
