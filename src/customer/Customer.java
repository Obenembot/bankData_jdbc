package customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import account.Account;
import person.Person;

public class Customer extends Person {

	private Account account;
	private String place_of_transaction;
	private LocalDate date_time;

	// private int account_number; // FOREIGN KEY (ACCOUNT)
	// private int id_number; // FOERIGN KEY (PERSON)

	public static List<Customer> customerList = null;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getPlace_of_transaction() {
		return place_of_transaction;
	}

	public void setPlace_of_transaction(String place_of_transaction) {
		this.place_of_transaction = place_of_transaction;
	}

	public LocalDate getDate() {
		return date_time;
	}

	public void setDate_time(LocalDate date) {
		this.date_time = date;
	}

	public Customer(Account account, String place_of_transaction, LocalDate date) {
		super();
		this.account = account;
		this.place_of_transaction = place_of_transaction;
		this.date_time = date;

	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((account == null) ? 0 : account.hashCode());

		result = prime * result + ((date_time == null) ? 0 : date_time.hashCode());
		result = prime * result + ((place_of_transaction == null) ? 0 : place_of_transaction.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;

		if (date_time == null) {
			if (other.date_time != null)
				return false;
		} else if (!date_time.equals(other.date_time))
			return false;
		if (place_of_transaction == null) {
			if (other.place_of_transaction != null)
				return false;
		} else if (!place_of_transaction.equals(other.place_of_transaction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [account=" + account + ", place_of_transaction=" + place_of_transaction + ", date_time="
				+ date_time + "," + super.toString() + " ]";
	}

	public static Connection connection;
	public static Statement statement;
	public ResultSet rs;

	String jdbc = "jdbc:mysql://localhost:3306/banking_system_db";
	String user = "root";
	String pass = "pvi@2019";

	public void registerCustomer(int id_number, String name, String surname, String email, long phone,
			String house_address, LocalDate date, String place, Account account) {

		// INSERTING INTO customer DATABASE
		setId_number(id_number);
		setName(name);
		setSurname(surname);
		setEmail_address(email);
		setPhone_number(phone);
		setHouse_address(house_address);
		setPlace_of_transaction(place);
		setDate_time(date);
		setAccount(account);
		this.account.setBalance(this.account.getAmount());

	}// end of registration method

	public int addToCustomer(int id_number, String name, String surname, String email, long phone, String house_address,
			LocalDate date) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbc, user, pass);
			statement = connection.createStatement();
			// INSERTING INTO customer DATABASE
			statement.executeUpdate("INSERT INTO customer (id_number, name, surname, house_address, email_address,"
					+ " phone_number, date)" + " VALUES(" + id_number + ",'" + name + "', '" + surname + "','"
					+ house_address + "','" + email + "'," + phone + ", '" + date + "')");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			;

		}
		return id_number;

	}

	public void addTransaction(double amount, LocalDate date, String place, long account_number, String reference,
			String type) {

		try {
			statement.executeUpdate(
					"INSERT INTO transactions (account_number, amount, date, place_of_transaction, reference, type)"
							+ " VALUES(" + account_number + "," + amount + ",'" + date + "','" + place + "','"
							+ reference + "','" + type + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public long deposit(long accountNumber, double amount) {

		if (amount > 0) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbc, user, pass);
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * FROM account");

				while (rs.next()) {

					if (rs.getDouble("account_number") == accountNumber) {

						double balance = rs.getDouble("balance") + amount;
						statement.executeUpdate(
								"UPDATE account Set balance = " + balance + "WHERE account_number =" + accountNumber);

						System.out.println("DONE!! \n thanks for trusting US!!");
						break;
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				// TODO: handle exception
			}
		} else {
			System.out.println("Amount is Either Zero");
		}
		return accountNumber;

	} // end of deposit

	public int withdraw(int account_number, double amount) {
		long acc = 0;
		if (amount > 0) {

			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbc, user, pass);
				statement = connection.createStatement();
				rs = statement.executeQuery("SELECT * FROM account");

				while (rs.next()) {
					double balance = rs.getDouble("balance") - amount;
					acc = rs.getInt("account_number"); // setting account_number

					statement.executeUpdate(
							"UPDATE account SET balance =" + balance + "WHERE  account_number =" + account_number);

					System.out.println("DONE!! \n thanks for trusting US!!");

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				// TODO: handle exception
			}

		} else if (amount <= 0) {
			System.out.println("Amount is Zero");
			System.exit(0);
		}
		return (int) acc;

	}// END OD WITHDRAWAL METHOD

	public int chechBalance(int account_number) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbc, user, pass);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM account 	WHERE account_number=" + account_number);

			while (rs.next()) {

				System.out.print("Your Balance is R");
				System.out.println(rs.getDouble("balance"));

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
		return account_number;
	}// END OF CHECK BALANCE METHOD

	public String[] printStatement(long accountNumber, LocalDate date) {
		String print[] = new String[100];
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbc, user, pass);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM transactions t WHERE   t.date ='" + date + "'"
					+ "AND t.account_number =" + accountNumber);

			int a = 0;
			while (rs.next()) {

				print[a] = rs.getInt(1) + "     :" + rs.getDouble(2) + "    : " + rs.getDate(3) + "      :"
						+ rs.getString(4) + "             : " + rs.getString(5) + "     :" + rs.getString(6);

				a++;
			}
		} catch (Exception e) {
			e.getStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return print;
	}
}
