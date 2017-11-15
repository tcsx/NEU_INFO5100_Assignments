public class Assignment4 {
    /**
     * Question 1.
     */
    public static String format(String s, int k) {
        char[] c = s.toCharArray();
        char[] newC = new char[c.length / k + c.length];
        for (int i = c.length - 1, j = 1, l = newC.length - 1; i >= 0; i--) {
            if (j == k + 1) {
                j = 1;
                newC[l] = '-';
                l--;
            }
            // Try to avoid hard code
            if (c[i] > 97 && c[i] < 122) {
                c[i] = (char) ((int) c[i] - 32);
            }
            if (c[i] != '-') {
                newC[l] = c[i];
                l--;
                j++;
            }
        }
        return String.valueOf(newC).trim();
    }

    /**
    * Question 5
    */
    public static String intToRoman(int num) {
        String[][] romanChar = { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
                { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
                { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, { "", "M", "MM", "MMM" } };
        String roman = "";
        for(int i = 0; num > 0; i++, num/=10) {
            int digit = num % 10;
            roman = romanChar[i][digit] +roman;
        }
        return roman;
    }

    /**
     * Question 6
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int half = (m+n)/2 + 1;
        int first = 0;
        int second = 0;
        int[] a = new int[half];
        int i = 0;
        while(i < half && first < m && second < n) {
            if (nums1[first] < nums2[second]) {
                a[i++] = nums1[first++];
            }else{
                a[i++] = nums2[second++];
            }
        }
        while(i < half){
            if(first == m){
                a[i++] = nums2[second++];
            }else{
                a[i++] = nums1[first++];
            }
        }
        if((m+n) % 2 == 0)        
            return (a[half-2] + a[half -1])/2.0;
        return a[half -1];
    }

    public static void main(String[] args) {
        Assignment4 a = new Assignment4();
        RockPaperScissorsGame.main(null);
        Driver.main(null);
        Test.main(null);
        System.out.println(intToRoman(3999));
        System.out.println(intToRoman(999));
        System.out.println(intToRoman(99));
        System.out.println(intToRoman(9));
        System.out.println(a.findMedianSortedArrays(new int[]{1,3}, new int[]{2,4,6}));
        System.out.println(a.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(a.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(a.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4,5}));
        System.out.println(a.findMedianSortedArrays(new int[]{1,2,9,11}, new int[]{4,10}));
    }
}

/**
 * Question 2
 */
abstract class Tool {

    protected int strength;
    protected char type;

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public abstract boolean fight(Tool t);
}

class Scissors extends Tool {
    public Scissors(int strength) {
        setStrength(strength);
        ;
        type = 's';
    }

    public boolean fight(Tool t) {
        if (t.type == 's')
            return this.strength > t.strength;
        if (t.type == 'p')
            return (this.strength * 2) > t.strength;
        return (this.strength / 2) > t.strength;
    }
}

class Paper extends Tool {
    public Paper(int strength) {
        setStrength(strength);
        ;
        type = 'p';
    }

    public boolean fight(Tool t) {
        if (t.type == 's')
            return (this.strength / 2) > t.strength;
        if (t.type == 'p')
            return this.strength > t.strength;
        return (this.strength * 2) > t.strength;
    }
}

class Rock extends Tool {
    public Rock(int strength) {
        setStrength(strength);
        type = 'r';
    }

    public boolean fight(Tool t) {
        if (t.type == 's')
            return (this.strength * 2) > t.strength;
        if (t.type == 'p')
            return (this.strength / 2) > t.strength;
        return this.strength > t.strength;
    }
}

class RockPaperScissorsGame {

    public static void main(String args[]) {

        Scissors s = new Scissors(5);
        Paper p = new Paper(7);
        Rock r = new Rock(15);

        System.out.println(s.fight(p) + " , " + p.fight(s));
        System.out.println(p.fight(r) + " , " + r.fight(p));
        System.out.println(r.fight(s) + " , " + s.fight(r));

    }
}

/**
 * Question 3
 */
class IpAddress {
    private String dottedDecimal;
    private int firstOctet, secondOctet, thirdOctet, fourthOctet;

    public IpAddress(String ip) {
        dottedDecimal = ip;
        String[] octets = splitByDot(ip);
        firstOctet = Integer.parseInt(octets[0]);
        secondOctet = Integer.parseInt(octets[1]);
        thirdOctet = Integer.parseInt(octets[2]);
        fourthOctet = Integer.parseInt(octets[3]);
    }

    public static String[] splitByDot(String s) {
        int first = s.indexOf('.');
        int second = s.indexOf('.', first + 1);
        int third = s.indexOf('.', second + 1);
        String[] octets = new String[4];
        octets[0] = s.substring(0, first);
        octets[1] = s.substring(first + 1, second);
        octets[2] = s.substring(second + 1, third);
        octets[3] = s.substring(third + 1, s.length());
        return octets;
    }

    public String getDottedDecimal() {
        return dottedDecimal;
    }

    public int getOctet(int i) {
        if (i == 1)
            return firstOctet;
        if (i == 2)
            return secondOctet;
        if (i == 3)
            return thirdOctet;
        return fourthOctet;
    }
}

class Driver {
    public static void main(String args[]) {
        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));
    }
}

/**
 * Question 4
 */
class Student {
    String name;
    String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}

class Course {
    String name;
    int numberOfStudent;
    Student[] students;

    public Course(String name) {
        this.name = name;
        numberOfStudent = 0;
        students = new Student[10];
    }

    public Student[] getStudents() {
        return students;
    }

    public boolean isFull() {
        return (numberOfStudent >= 10);
    }

    public void registerStudent(Student student) {
        if (isFull()) {
            System.out.println("Unable to register. This course is full.");
            return;
        }
        students[numberOfStudent++] = student;
    }
}

class Test {
    public static void main(String[] args) {
        Student s1 = new Student("a", "1");
        Student s2 = new Student("b", "2");
        Student s3 = new Student("c", "3");
        Student s4 = new Student("d", "4");
        Student s5 = new Student("e", "5");
        Student s6 = new Student("f", "6");
        Student s7 = new Student("g", "7");
        Student s8 = new Student("h", "8");
        Student s9 = new Student("i", "9");
        Student s10 = new Student("j", "10");
        Student s11 = new Student("k", "11");

        Course c = new Course("Java");
        c.registerStudent(s1);
        c.registerStudent(s2);
        c.registerStudent(s3);
        c.registerStudent(s4);
        c.registerStudent(s5);
        c.registerStudent(s6);
        c.registerStudent(s7);
        c.registerStudent(s8);
        c.registerStudent(s9);
        c.registerStudent(s10);
        c.registerStudent(s11);
        Student[] ss = c.getStudents();
        for (Student s : ss) {
            System.out.println(s.getName() + ", " + s.getId());
        }
    }
}
