public class DessertShoppe{
    public static final String NAME = "Java";
    public static final double TAX_RATE = 0.065;
    public static final int MAX_SIZE_OF_NAME = 30;  
    public static final int WIDTH = 7;
    
    public static String cents2dollarsAndCents(int i){
        return String.format("%.2f", i/100.0);
    }


    public static void main(String[] args) {
        System.out.println(cents2dollarsAndCents(120));
    }
}