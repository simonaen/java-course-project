package courseproject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Customer {
    private Store store;
    private ConcurrentHashMap<Goods, Integer> goodsToBuy;

    public Customer( Store store) {
        this.store = store;
        goodsToBuy = new ConcurrentHashMap<>();
        store.addCustomers(this);
    }

    public void addToShoppingCart(Goods goods, int quantity) {
        for (int i = 0; i < quantity; i++) {
            goodsToBuy.put(goods, quantity);
        }
    }

    public ConcurrentHashMap<Goods, Integer> getGoodsToBuy() {
        return goodsToBuy;
    }

    public int mapSize(){
        return goodsToBuy.size();
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", store=" + store +
                ", goodsToBuy=" + goodsToBuy +
                '}';
    }
}
