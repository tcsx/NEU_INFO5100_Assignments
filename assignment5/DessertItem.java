public abstract class DessertItem {
    private String name;

    public DessertItem() {
    }

    public DessertItem(String name) {
        this.name = String.format("%-" + DessertShoppe.MAX_SIZE_OF_NAME + "s", name);
    }

    public final String getName() {
        return name;
    }

    public abstract int getCost();

}