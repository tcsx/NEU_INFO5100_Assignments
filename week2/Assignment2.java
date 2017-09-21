import java.util.LinkedList;
import java.util.List;

public class Assignment2 {
    /**
     * Task1.
     */
    public double employeeSalary(double hours) {
        if (hours <= 36) {
            return hours * 15;
        } else if (hours <= 41) {
            return 36 * 15.0 + (hours - 36) * 15 * 1.5;
        } else if (hours <= 48) {
            return 36 * 15.0 + 5 * 15 * 1.5 + (hours - 41) * 30;
        } else {
            return -1;
        }
    }

    /**
     * Task2.
     */
    public int addDigits(int input) {
        if (input >= 0) {
            int digit = input % 10;
            input /= 10;
            if (input != 0)
                return digit + addDigits(input);
            return digit;
        } else {
            return addDigits(-input);
        }
    }

    /**
     * Task3.
     */
    public void printPerfectNumbers(int n) {
        for (int i = 2; i <= n; i++) {
            int sum = 1;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                int m = i % j;
                if (m == 0) {
                    sum += (j + i / j);
                }
            }
            if (sum == i) {
                System.out.println(i);
            }
        }
    }

    /**
     * Task6.(Task 4 & 5 are behind this method)
     */
    public void printIsoscelesTriangle(int n) {
        if (n < 2) {
            System.out.println("Please give a number larger than 1.");
            return;
        }
        System.out.println('*');
        for (int i = 2; i <= n - 1; i++) {
            System.out.print('*');
            for (int j = 0; j < i - 2; j++) {
                System.out.print(' ');
            }
            System.out.println('*');
        }
        for (int i = 0; i < n - 1; i++) {
            System.out.print('*');
        }
        System.out.println('*');
    }

    /**
     * Task4.
     */
    public static class Pizza {
        String pizzaType;
        Double unitPrice;
        Double loyaltyPoints;
        int number;

        public Pizza(String type, Double price, double points, int num) {
            pizzaType = type;
            unitPrice = price;
            loyaltyPoints = points;
            number = num;
        }

        public Pizza(String type, Double price, int num) {
            this(type, price, 0, num);
        }
    }

    /**
     * Task5.
     */
    public static class Customer {
        String name;
        List<Pizza> order;
        /* Answer to 5.ii.: I will just put pizza name(as pizza type) and 
        number into the Pizza class since these features are closely related 
        to Pizza itself. Besides, put them in the same class enables me to 
        access them conveniently and precisely. Then put different pizza 
        instances into a List because List is resizable and easy to iterate.
        */

        public Customer(String name) {
            this.name = name;
        }

        public Customer(String name, List<Pizza> order) {
            this.name = name;
            this.order = order;
        }

    }

    public static void main(String[] args) {
        List<Pizza> order = new LinkedList<Pizza>();
        order.add(new Pizza("Cream Pizza", 5.5, 2));
        order.add(new Pizza("Vegan Pizza", 5.7, 3));
        order.add(new Pizza("Hot Pizza", 6.7, 1));
        Double bill = 0.0;
        for (Pizza pizza : order) {
            bill += pizza.unitPrice * pizza.number;
        }
        System.out.printf("Your Bill: $%.2f", bill);
    }
}
