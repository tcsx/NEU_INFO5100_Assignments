public class Cookie extends DessertItem {
    private int number;
    private int price;

    public Cookie(String name, int number, int price) {
        super(name);
        this.number = number;
        this.price = price;
    }

    @Override
    public int getCost() {
        return (int) Math.round(number * price / 12.0);
    }

    public String numberAndPrice(){
        return number + " @ " + DessertShoppe.cents2dollarsAndCents(price) + " /dz.";
    }
}