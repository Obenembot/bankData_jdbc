package card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import account.Account;

public class Card {

	private int pin;
	private String cardType;

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Card(int pin, String cardType) {
		super();
		this.pin = pin;
		this.cardType = cardType;
	}

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + pin;
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
		Card other = (Card) obj;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		if (pin != other.pin)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Card [pin=" + pin + ", cardType=" + cardType + "]";
	}

	public void addCard(int pin, String card_type, int accountNumber) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
					"pvi@2019");
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO card (pin, card_type, account_number) VALUES(" + pin + ",'" + card_type
					+ "'," + accountNumber + ")");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}

}
