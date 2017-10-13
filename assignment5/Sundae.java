public class Sundae extends IceCream {
    private int costOfTopping;

    public Sundae(String name, int cost, String toppingName, int costOfTopping){
        super(toppingName + " Sundae with\n" + String.format("%-" + DessertShoppe.MAX_SIZE_OF_NAME + "s", name), cost);
        this.costOfTopping = costOfTopping;
    }

    @Override
    public int getCost() {
        return super.getCost() + costOfTopping;
    }

}