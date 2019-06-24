package courseproject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Receipt {
    private static int receiptID = 0;
    private int id;
    private Cashier cashier;
    private Date dateOfIssuing;
    private String date;
    private List<Goods> boughtGoods;
    private double total;

    public Receipt(Cashier cashier, List<Goods> boughtGoods) {
        receiptID ++;
        id = receiptID;
        this.cashier = cashier;
        dateOfIssuing = new Date();
        this.boughtGoods = boughtGoods;
    }

    public int getId() {
        return id;
    }

    public String displayGoodsList(){
        String s = "";
        for (Goods g:boughtGoods
             ) {
            s += g.getName() + " | " + g.getPrice() + "\n";
            total +=g.getPrice();
        }
        String formatted = String.format("%.2f",total);
        s += "\nTotal: " + formatted + "\n\n";
        total = 0;
        return s;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

        String date = format.format(dateOfIssuing);
        return "Receipt " +
                "ID:" + id +
                "\nCashier: " + cashier +
                "\nDate: " + date +
                "\n" +displayGoodsList();
    }
}
