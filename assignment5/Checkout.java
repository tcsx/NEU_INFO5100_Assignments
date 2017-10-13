import java.util.StringJoiner;
import java.util.Vector;

public class Checkout {
    Vector<DessertItem> items;

    public Checkout() {
        items = new Vector<DessertItem>();
    }

    public int numberOfItems() {
        return items.size();
    }

    public void enterItem(DessertItem item) {
        items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public int totalCost() {
        int cost = 0;
        for (DessertItem d : items) {
            cost += d.getCost();
        }
        return cost;
    }

    public int totalTax() {
        return (int) Math.round(DessertShoppe.TAX_RATE * totalCost());
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("         M & M Dessert Shoppe         ");
        sj.add("         --------------------         ");
        for (DessertItem item : items) {
            if (item instanceof Candy) {
                sj.add(((Candy)item).weightAndPrice());
            }
            if (item instanceof Cookie) {
                sj.add(((Cookie)item).numberAndPrice());
            }
            String cost = String.format("%" + DessertShoppe.WIDTH + "s", DessertShoppe.cents2dollarsAndCents(item.getCost()));
            sj.add(item.getName() + " " + cost);
        }
        sj.add("");
        String taxString = String.format("%-" + DessertShoppe.MAX_SIZE_OF_NAME + "s", "Tax");
        String taxAmount = String.format("%" + DessertShoppe.WIDTH + "s", DessertShoppe.cents2dollarsAndCents(totalTax()));
        sj.add(taxString + " " + taxAmount);
        String costString = String.format("%-" + DessertShoppe.MAX_SIZE_OF_NAME + "s", "Total Cost");
        String costAmount = String.format("%" + DessertShoppe.WIDTH + "s", DessertShoppe.cents2dollarsAndCents(totalCost() + totalTax()));
        sj.add(costString + " " + costAmount);
        return sj.toString();
    }

}
