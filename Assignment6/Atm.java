import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Atm implements Serializable {

	static class UserInfo implements Serializable {
		private static final long serialVersionUID = 3414971247426173706L;
		private String password;
		private User user;
		private double availableBalance;
		private LinkedList<String> recentTransactions;

		public UserInfo(String password, User user, double availableBalance, LinkedList<String> recentTransactions) {
			this.password = password;
			this.user = user;
			this.availableBalance = availableBalance;
			this.recentTransactions = recentTransactions;
		}
	}

	private static final long serialVersionUID = 5200128620073075440L;
	private double availableAmountInMachine;
	private double transactionFee;
	private HashMap<String, UserInfo> userData;
	public static final String DATAFILE = "Assignment6/userData.dat";

	public Atm(double availableAmountInMachine, double transactionFee) {
		this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
		ObjectInputStream objIn = null;
		try {
			objIn = new ObjectInputStream(new FileInputStream(new File(DATAFILE)));
			userData = (HashMap<String, UserInfo>) objIn.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				objIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String login(Scanner sc) {
		if (askIfNew(sc)) {
			createNewAccount(sc);
		}
		return validate(sc);
	}

	public boolean askIfNew(Scanner sc) {
		System.out.println("Are you a new user? If you are a new user, please enter \"yes\". If you are a current user, please enter \"no\".");
		String response = sc.nextLine().trim();
		if (!response.isEmpty()) {
			if ("no".equalsIgnoreCase(response)) {
				return false;
			} else if ("yes".equalsIgnoreCase(response)) {
				return true;
			} else {
				System.out.println("Please enter Yes or No.");
				return askIfNew(sc);
			}
		} else {
			System.out.println("Please enter Yes or No.");
			return askIfNew(sc);
		}
	}

	public String createNewAccount(Scanner sc) {
		String name;
		int age;
		String address;
		String phoneNumber;
		String password;
		System.out.println("Please enter your name, age, address, phoneNumber and password in separate lines.");
		try {
			name = sc.nextLine().trim();
			age = Integer.parseInt(sc.nextLine().trim());
			address = sc.nextLine().trim();
			phoneNumber = sc.nextLine().trim();
			password = sc.nextLine().trim();
            if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() 
                ||!phoneNumber.matches("\\d{10}") ||password.isEmpty() ) {
				throw new Exception();
			}
			String num = generateNewAccountNum();
			UserInfo newUser = new UserInfo(password, new User(name, age, address, phoneNumber, num), 0, new LinkedList<String>());
            userData.put(num, newUser);
            storeUserData();
            System.out.println("Congratulations! You have created your new account.\nYour account number is: " + num);
            return num;
		} catch (Exception e) {
			System.out.println("Sorry, your information is incomplete or incorrect. Please try again.");
			return createNewAccount(sc);
		}
	}

	public String generateNewAccountNum() {
		int n = (int) (Integer.MAX_VALUE * Math.random());
		String num = String.format("%010d", n);
		if (userData.containsKey(num)) {
			return generateNewAccountNum();
		}
		return num;
	}

	public void storeUserData() {
		ObjectOutputStream objOut = null;
		try {
			objOut = new ObjectOutputStream(new FileOutputStream(new File(DATAFILE)));
			objOut.writeObject(userData);
			objOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				objOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String validate(Scanner sc) {
		System.out.println("Please enter your account number.");
		String accountNum = sc.nextLine().trim();
		if (!userData.containsKey(accountNum)) {
			System.out.println("Incorrect account number. Please enter again.");
			return validate(sc);
		}
		System.out
				.println("Please enter your password. If you forgot your password, please enter \"forgot password\".");
		String pwd = sc.nextLine().trim();
		if (pwd.equals(getPassword(accountNum))) {
			System.out.println("You are currently logged in.");
			return accountNum;
		}
		if (pwd.equalsIgnoreCase("forgot password")) {
            resetPassword(sc, accountNum);
            return validate(sc);
		} else {
			System.out.println("Sorry, your password does not match your account number. Please enter again.");
			return validate(sc);
		}
	}

	public void resetPassword(Scanner sc, String accountNum) {
		System.out.println("To validate your identity, please enter your name, age and phoneNumber in seperate lines.");
		String name;
		int age;
		String phoneNumber;
		try {
			name = sc.nextLine().trim();
			age = Integer.parseInt(sc.nextLine().trim());
            phoneNumber = sc.nextLine().trim();
            if (!getName(accountNum).equals(name) 
                || !getPhoneNumber(accountNum).equals(phoneNumber)
                || getAge(accountNum) != age){
                    throw new Exception();
            }
            changePassword(sc, accountNum);
		} catch (Exception e) {
			System.out.println("Sorry, your information is incomplete or incorrect. Please try again.");
			resetPassword(sc, accountNum);
		}
	}

	public void changePassword(Scanner sc, String accountNum) {
		System.out.println("Please enter your new password");
		String pwd = sc.nextLine().trim();
		if (pwd.isEmpty()) {
			changePassword(sc, accountNum);
		}
        userData.get(accountNum).password = pwd;
        storeUserData();
        System.out.println("You have successfully changed your password.");
	}

	public String getPassword(String accountNum) {
		return userData.get(accountNum).password;
	}

	public String getName(String accountNum) {
		return userData.get(accountNum).user.getName();
	}

	public int getAge(String accountNum) {
		return userData.get(accountNum).user.getAge();
	}

	public String getPhoneNumber(String accountNum) {
		return userData.get(accountNum).user.getPhoneNumber();
	}

    public double getAvailableBalance(String accountNum){
        return userData.get(accountNum).availableBalance;
    }


    public void withDrawal(Scanner sc, String accountNum){
        System.out.println("please enter the amount of money you want to withdraw.");
        double amount = 0;
        try {
            amount = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter only numbers.");
            withDrawal(sc, accountNum);
            return;
        }
        double balance = getAvailableBalance(accountNum) - amount - transactionFee;
        double temp = availableAmountInMachine - amount;
        if (balance < 0){
            System.out.println("Sorry, you have not enough available balance. Please enter a smaller number.");
            withDrawal(sc, accountNum);
        }else if(temp < 0){
            System.out.println("Sorry, we have not enough money in this ATM. Please enter a smaller number.");
            withDrawal(sc, accountNum);
        }else{
            userData.get(accountNum).availableBalance = balance;
            availableAmountInMachine = temp;
            String trans = "Withdraw       " + String.format("%12.2f", amount);
            addRecentTransactions(accountNum, trans);
            storeUserData();
            System.out.println("You have withdrawn $" + amount +". Please take your money.");
        }
    }

    public void deposit(Scanner sc, String accountNum){
        System.out.println("please enter the amount of money you want to deposit.");
        double amount = 0;
        try {
            amount = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter only numbers.");
            deposit(sc, accountNum);
        }
        userData.get(accountNum).availableBalance += (amount - transactionFee);
        availableAmountInMachine += amount;
        String trans = "deposit        " + String.format("%12.2f", amount);
        addRecentTransactions(accountNum, trans);
        storeUserData();
        System.out.println("You have successfully deposited $" + amount);
    }

    public void addRecentTransactions(String accountNum, String trans){
        LinkedList<String> recentTransactions = userData.get(accountNum).recentTransactions;
        if(recentTransactions.size() < 10){
            recentTransactions.add(trans);
        }else{
            recentTransactions.removeFirst();
            recentTransactions.add(trans);
        }
    }

    public void recentTransactions(String accountNum){
        for (String s : userData.get(accountNum).recentTransactions){
            System.out.println(s);
        }
    }

	public static void main(String[] args) {
		Atm atm = new Atm(100000000, 5);
		Scanner sc = new Scanner(System.in);
        String accountNum = atm.login(sc);
        System.out.println("Now choose your operation by entering an operation name. Available operations are availableBalance, withDrawal, deposit, recentTransactions, changePassword and exit.");
        String operation = "";
        while((operation = sc.nextLine().trim()) != null){
            if ("availableBalance".equals(operation)){
                System.out.println("Your current available balance is: " + atm.getAvailableBalance(accountNum));
            }else if("withDrawal".equals(operation)){
                atm.withDrawal(sc, accountNum);
            }else if ("deposit".equals(operation)){
                atm.deposit(sc, accountNum);                
            }else if("recentTransactions".equals(operation)){
                atm.recentTransactions(accountNum);
            }else if("changePassword".equals(operation)){
                atm.changePassword(sc, accountNum);
            }else if("exit".equals(operation)){
                return;
            }else{
                System.out.println("Invalid operation. Please enter again.");
            }
            System.out.println("Continue by entering an operation name. ");
        }
        sc.close();
	}
}
