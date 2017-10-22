import static org.junit.Assert.*;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class AtmTest {
	private static Scanner sc;

	@BeforeClass
	public static void initiate(){
		sc = new Scanner(System.in);
	}

	@AfterClass
	public static void close(){
		sc.close();
	}

	@Test
	@SuppressWarnings("all")
	public void testStoreUserData() {
		User u = new User("name", 9, "address", "9999999999", "0000000000");
		Atm.UserInfo ui = new Atm.UserInfo("password", u, 8888, new LinkedList<String>());
		User u1 = new User("name1", 99, "address1", "9999999998", "1111111111");
		Atm.UserInfo ui1 = new Atm.UserInfo("password1", u1, 888888, new LinkedList<String>());
		User u2 = new User("name2", 999, "address2", "9999999997", "1111111112");
		Atm.UserInfo ui2 = new Atm.UserInfo("password2", u2, 8888888, new LinkedList<String>());
		ObjectOutputStream objOut = null;
		ObjectInputStream objIn = null;
		HashMap<String, Atm.UserInfo> usd = new HashMap<>();
		usd.put("0000000000", ui);
		usd.put("1111111111", ui1);
		usd.put("1111111112", ui2);
		HashMap<String, Atm.UserInfo> map;
		try {
			objOut = new ObjectOutputStream(new FileOutputStream(new File(Atm.DATAFILE)));
			objOut.writeObject(usd);
			objOut.flush();
			objIn = new ObjectInputStream(new FileInputStream(new File(Atm.DATAFILE)));
			map = (HashMap<String, Atm.UserInfo>) objIn.readObject();
			assertTrue(map.containsKey("0000000000"));
			assertTrue(map.containsKey("1111111111"));
			assertTrue(map.containsKey("1111111112"));
			assertFalse(map.containsKey("1111111113"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				objOut.close();
				objIn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testAtm() {
		Atm atm = new Atm(100000000, 5);
		assertEquals("password", atm.getPassword("0000000000"));
		assertEquals("name", atm.getName("0000000000"));
		assertEquals(9, atm.getAge("0000000000"));
		assertEquals("9999999999", atm.getPhoneNumber("0000000000"));
	}

	@Test
	public void testAskIfNew() {
		Atm atm = new Atm(100000000, 5);
		assertTrue(atm.askIfNew(sc));
	}

	@Test
	public void testGenerateNewAccountNum() {
		Atm atm = new Atm(100000000, 5);
		System.out.println(atm.generateNewAccountNum());
	}

	@Test
	public void testCreateNewAccount() {
		Atm atm = new Atm(100000000, 5);
		String num = atm.createNewAccount(sc);
		assertEquals("correct", atm.getPassword(num));
	}

	@Test
	public void testChangePassword() {
		Atm atm = new Atm(100000000, 5);
		atm.changePassword(sc, "1111111111");
		assertEquals("changed", atm.getPassword("1111111111"));
	}

	@Test
	public void testResetPassword() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = "1111111112";
		atm.resetPassword(sc, accountNum);
		assertEquals("reset", atm.getPassword(accountNum));
	}

	@Test
	public void testValidate() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = atm.validate(sc);
		assertEquals("1111111112", accountNum);
		assertEquals("reset2", atm.getPassword(accountNum));
	}

	@Test
	public void testLogin() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = atm.login(sc);
		assertEquals("1111111112", accountNum);
		assertEquals("reset3", atm.getPassword(accountNum));
	}

	@Test
	public void testGetAvailableBalance() {
		Atm atm = new Atm(100000000, 5);
		assertEquals(8888, atm.getAvailableBalance("0000000000"), 0.0);
	}

	@Test
	public void testAddRecentTransactions() {
		Atm atm = new Atm(100000000, 5);
		double amount = 100;
		String accountNum = "1111111111";
		for (int i = 0; i < 17; i++) {
			String trans = "deposit        " + String.format("%12.2f", amount++);
			atm.addRecentTransactions(accountNum, trans);
		}
		atm.recentTransactions(accountNum);
	}

	@Test
	public void testRecentTransactions() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = "1111111111";
		atm.recentTransactions(accountNum);
	}

	@Test
	public void testDeposit() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = "1111111111";
		double balance = atm.getAvailableBalance(accountNum);
		atm.deposit(sc, accountNum);
		assertEquals(balance + 1000 - 5, atm.getAvailableBalance(accountNum), 0.0);
		atm.recentTransactions(accountNum);
	}

	@Test
	public void testWithDrawal() {
		Atm atm = new Atm(100000000, 5);
		String accountNum = "1111111111";
		double balance = atm.getAvailableBalance(accountNum);
		atm.withDrawal(sc, accountNum);
		assertEquals(balance - 105, atm.getAvailableBalance(accountNum), 0.0);
		atm.recentTransactions(accountNum);
	}
}
