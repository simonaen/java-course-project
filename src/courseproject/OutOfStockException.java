package courseproject;

public class OutOfStockException extends Exception{
    private int quantity;
    private int id;

    public OutOfStockException(int quantity, int id) {
        this.quantity = quantity;
        this.id = id;
    }

    @Override
    public String toString() {
        return "OutOfStockException:\n" +
                "We are currently: " + quantity +
                " pieces short of product #" + id;
    }
}
