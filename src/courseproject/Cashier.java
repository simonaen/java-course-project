package courseproject;

public class Cashier {
    private static int cashierID;
    private int id;
    private String name;

    public Cashier(String name) {
        cashierID++;
        this.id = cashierID;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cashier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
