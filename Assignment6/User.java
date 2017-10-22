import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -530345065693700925L;
	private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private String bankAccountNumber;

    public User(String name, int age, String address, String phoneNumber, String bankAccountNumber) {
		super();
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccountNumber = bankAccountNumber;
    }
    
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
}