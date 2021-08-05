package back_end.model;

public class Bill {
    private int id;
    private String name;
    private double price;
    private  int quantity;
    private double bill;
    private String time;

    public Bill(){
    }

    public Bill(int id, String name, double price, int quantity, double bill, String time) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.bill = bill;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", bill=" + bill +
                ", time='" + time + '\'' +
                '}';
    }
}
