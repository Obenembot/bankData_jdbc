package account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import customer.Customer;

public class Account {
	private long account_number; // PRIMARY KEY
	private double balance;
	private double amount;
	private String accountType;

	// private int customer_id; // FOREIGN KEY (CUSTOMER) // pass this when
	// inserting to the database
	// private int id_number; // FOREIGN KEY (PERSON)

	public long getAccount_number() {
		return account_number;
	}

	public void setAccount_number(long account_number) {
		this.account_number = account_number;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + (int) (account_number ^ (account_number >>> 32));
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (account_number != other.account_number)
			return false;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		return true;
	}

	public Account(long account_number, double balance, double amount, String accountType) {
		super();
		this.account_number = account_number;
		this.balance = balance;
		this.amount = amount;
		this.accountType = accountType;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int addToAccount(Account ac, double balance, String account_type, int id_number) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
					"pvi@2019");
			Statement statement = connection.createStatement();
			// INSERTING INTO ACCOUNT DATABASE
			statement.executeUpdate("INSERT INTO account (account_number, balance, account_type, id_number) VALUES("
					+ ac.getAccount_number() + "," + balance + ",'" + account_type + "'," + id_number + ")");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return (int) ac.getAccount_number();
	}

}
