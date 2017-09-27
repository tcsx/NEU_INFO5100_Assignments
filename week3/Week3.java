import java.util.Arrays;

/**
 * 1. Find the error in the following code and explain in few lines why it is wrong.
 */
public class Week3 {
    public class Book {
        int size;
        int price;
        String name;

        public Book(int size) {//Error. This constructor conflicts with another constructor  public Book(int price), because they have same method signature.
            this.size = size;
        }

        public Book(int size, int price, String name) {
            super();
            this.size = size;
            this.price = price;
            this.name = name;
        }

        public Book(int price) {//Error. Conflict explained above.
            this.price = price;
        }

        public setName(String name){//Error because the return type for this method is missing.
            return name;
        }
    }

    /**
     * 2. Find the error in the following code and explain in few lines why it is wrong.
     */
    class Clock {
        String time;

        void getTime() {//Error. The return type for this method is void, but it returns String in its body.
            return time;
        }

        void setTime(String t) {
            time = t;
        }
    }

    /**
     * 3. Write a Java function to remove vowels in a string. (Score 2)
     * i. The function should take a string as input.
     * ii. Should return the input string after omitting the vowels.
     */
    public static String removeVowelsFromString(String input) {
        if (input == null)
            return null;
        final String VOWEL = "[aeiouAEIOU]+";
        return input.replaceAll(VOWEL, "");
    }

    /**
     * 4. Write a java function to check if two strings are Anagrams or not. (Score 2)
     * i. The function should take two input strings.
     * ii. Should return a boolean 'true' if the inputs are Anagrams else return 'false'.
     */
    public static boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
        if (s1 == null || s2 == null)
            return false;
        if (s1.length() != s2.length())
            return false;
        if (s1.equals(s2))
            return false;
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        Arrays.sort(s1Array);
        Arrays.sort(s2Array);
        for (int i = 0; i < s1.length(); i++) {
            if (s1Array[i] != s2Array[i])
                return false;
        }
        return true;
    }

    /**
     * 5. Create a calculator that can perform the following features. (Total Score 4)
     * i. The calculator should be able to perform Addition, subtraction, multiplication, division. (Score 2)
     * ii. Should be able to perform squareRoot, square, cube. (Score 1)
     * iii. Should be able to convert 'Fahrenheit-Celsius' , 'Feet-Inches'. (Score 1)
     * The calculator should be able to solve a quadratic equation and return the solution as array.
     * i. This function should take three arguments.
     * ii. For example, if quadratic equation is Ax2 + Bx + C. The function should take A,B,C as arguments and return a solution as array
     */
    public static class Calculator {
        public static double add(double a, double b) {
            return a + b;
        }

        public static double minus(double a, double b) {
            return a - b;
        }

        public static double dividedBy(double a, double b) {
            if (b == 0) {
                System.out.println("Error: Divided by 0.");
                if (a < 0)
                    return Double.NEGATIVE_INFINITY;
                else if (a == 0)
                    return Double.NaN;
                else
                    return Double.POSITIVE_INFINITY;
            }
            return a / b;
        }

        public static double multiply(double a, double b) {
            return a * b;
        }

        public static double squreRoot(double a) {
            if (a < 0) {
                System.out.println("Error: Negative operator for squre root.");
            }
            return Math.sqrt(a);
        }

        public static double square(double a) {
            return a * a;
        }

        public static double cube(double a) {
            return a * a * a;
        }

        public static double convertFahrenheitToCelsius(double a) {
            return (a - 32.0) / 1.8;
        }

        public static double convertCelsiusToFahrenheit(double a) {
            return (a * 1.8 + 32.0);
        }

        public static double covertFeetToInches(double a) {
            return a * 12;
        }

        public static double covertInchesToFeet(double a) {
            return a / 12;
        }

        public static double[] solutionOfQuadraticEquation(double a, double b, double c) {
            if (a == 0) {
                if (b == 0) {
                    return null;
                } else {
                    return new double[] { -c / b, Double.NaN };
                }
            }
            double delta = b * b - 4 * a * c;
            if ((delta) < 0) {
                System.out.println("No solution.");
                return null;
            }
            double[] solution = new double[2];
            solution[0] = (-b + Math.sqrt(delta)) / (2 * a);
            solution[1] = (-b - Math.sqrt(delta)) / (2 * a);
            return solution;
        }
    }
}