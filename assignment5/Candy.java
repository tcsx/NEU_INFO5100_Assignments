public class Candy extends DessertItem{
    private double weight;
    private int price;

    public Candy(String name, double weight, int price){
        super(name);
        this.weight = weight;
        this.price = price;
    }

    @Override
    public int getCost() {
        return (int)Math.round(weight * price);
    }

    public String weightAndPrice(){
        return String.format("%.2f", weight) + " lbs. @ " + DessertShoppe.cents2dollarsAndCents(price) + " /lb.";
    }
}