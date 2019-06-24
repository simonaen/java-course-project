package courseproject;

public class Goods {
    private static int goodsID = 0;
    private int id;
    private String name;
    private double price;
    private int expirationPeriod;

    public Goods(String name, double price, int expirationPeriod) {
        goodsID++;
        id = goodsID;
        this.name = name;
        this.price = price;
        this.expirationPeriod = expirationPeriod;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getExpirationPeriod() {
        return expirationPeriod;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expirationPeriod=" + expirationPeriod +
                '}';
    }
}
