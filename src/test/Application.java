package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import account.Account;
import card.Card;
import customer.Customer;
import employee.Employee;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Account account = new Account();
		Customer customer = new Customer();
		Scanner scanner = new Scanner(System.in);
		Employee employee = new Employee();
		Card card = new Card();

		Customer.customerList = new ArrayList<>();

		int main = 0; // to control the main loop
		int option = 0, pin = 0, customer_id = 0, id_number = 0;
		; // option and pin
		String name = null, surname = null, email = null, house_address = null, user_name = null, password = null,
				rePass = null, position = null, account_type = null, place = null, card_type = null, ref, type = null;
		;
		long phone = 0;
		int account_number = 0;
		double amount = 0;

		Connection connection;
		Statement statement;
		ResultSet rs;
		LocalDate date = null;

		em: while (true) {

			for (;;) {
				try {
					scanner.nextLine();
					System.out.println(
							"........................ \n1. to Register Employee \n2. to Login \n3. to Exit \n.....................");
					option = scanner.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("wrong input");

					// TODO: handle exception
				}
			}

			mainSwith: switch (option) {
			case 1:
				System.out.println("       \n...................... Employee Information ................. \n");
				scanner.nextLine();
				System.out.println("Name");
				name = scanner.nextLine();

				System.out.println("Surname");
				surname = scanner.nextLine();

				System.out.println("Email");
				email = scanner.nextLine();

				System.out.println("House address");
				house_address = scanner.nextLine();

				for (;;) {
					try {

						System.out.println("Id_number");
						id_number = scanner.nextInt();
						break;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						// TODO: handle exception

					}
				}
				for (;;) {
					try {

						System.out.println("Phone Number");
						phone = scanner.nextLong();
						break;
					} catch (Exception e) {
						System.out.println(e.getMessage());
						// TODO: handle exception

					}
				}
				date = LocalDate.now(); // to set to the day the employee got employed
				System.out.println("................. Employee logig Informaion ............. ");

				scanner.nextLine();
				System.out.println("Position");
				position = scanner.nextLine();

				forr: for (;;) {
					System.out.println("UserName");
					user_name = scanner.nextLine();

					System.out.println("Password");
					password = scanner.nextLine();

					System.out.println("Confirm Password");
					rePass = scanner.nextLine();

					if (rePass.equals(password)) {
						employee.setPassword(password);

						employee.registerEmployee(name, surname, phone, email, house_address, id_number, user_name,
								password, position, date);
						employee.addToEmployee(id_number, name, surname, phone, email, house_address, date, user_name,
								password, position);
						break forr;
					}
				}

				break mainSwith;
			case 2:
				scanner.nextLine();
				System.out.println("Enter Username");
				user_name = scanner.nextLine().trim();

				System.out.println("Enter Password");
				password = scanner.nextLine().trim();
				scanner.nextLine();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
							"pvi@2019");
					statement = connection.createStatement();
					rs = statement.executeQuery("SELECT * FROM employee");

					login: while (rs.next()) {
						if (rs.getString("user_name").equals(user_name) && rs.getString("password").equals(password)) {
							System.out.println(" \n Welcome:  " + rs.getString("surname") + " \n");

							System.out.println(
									"1. to create Customer Account \n2. to deposit \n3. to Chech Balance \n4. withdraw \n5. to Print Statement");
							try {
								option = scanner.nextInt();

							} catch (Exception e) {
								System.out.println("wrong input");
								// TODO: handle exception
							}
							scanner.nextLine();
							if (option == 1) {
								System.out.println(
										"       \n...................... Customer Information ................. \n");

								System.out.println("Name");
								name = scanner.nextLine();
								customer.setName(name);

								System.out.println("Surname");
								surname = scanner.nextLine();
								customer.setSurname(surname);

								System.out.println("Email");
								email = scanner.nextLine();
								customer.setEmail_address(email);

								System.out.println("House address");
								house_address = scanner.nextLine();
								customer.setHouse_address(house_address);
								try {

									System.out.println("Id_number");
									id_number = scanner.nextInt();
									customer.setId_number(id_number);

								} catch (Exception e) {
									System.out.println(e.getMessage());
									// TODO: handle exception

								}

								try {

									System.out.println("Phone Number");
									phone = scanner.nextLong();

								} catch (Exception e) {
									System.out.println(e.getMessage());
									// TODO: handle exception

								}

								scanner.nextLine();

								System.out.println(" \n..................Account Information ................... \n");

								System.out.println("Place of transaction");
								place = scanner.nextLine();

								scanner.nextLine();
								System.out.println("Enter Account Number");
								account.setAccount_number(Integer.parseInt(scanner.next()));

								System.out.println("Enter PIn");
								pin = scanner.nextInt();

								scanner.nextLine();
								System.out.println("Card Type");
								card_type = scanner.nextLine();

								System.out.println("Account Type");
								account_type = scanner.nextLine();

								scanner.nextLine();
								System.out.println("Amount To Deposit");
								amount = scanner.nextDouble();

								scanner.nextLine();
								System.out.println("Reference");
								ref = scanner.nextLine();

								date = LocalDate.now();

								customer.registerCustomer(id_number, name, surname, email, phone, house_address, date,
										place, account);

								// customer Data.........................
								id_number = customer.addToCustomer(id_number, user_name, surname, email, phone,
										house_address, date);

								account_number = account.addToAccount(account, amount, account_type, id_number);// Account
								// DATA

								customer.addTransaction(amount, date, place, account_number, ref, "deposit");
								card.addCard(pin, card_type, account_number); //
								// CardData......................

								// ................................

							} else if (option == 2) {

								scanner.nextLine();
								System.out.println("Enter Account Number");
								account_number = Integer.parseInt(scanner.next());

								System.out.println("Enter Amount");
								amount = scanner.nextDouble();

								scanner.nextLine();
								System.out.println("Transaction Place");

								place = scanner.nextLine();
								System.out.println("Reference");
								ref = scanner.nextLine();

								date = LocalDate.now();

								customer.deposit(account_number, amount);
								type = "deposit";
								customer.addTransaction(amount, date, place, account_number, ref, type);
							} else if (option == 3) {
								System.out.println("Enter Account_number");
								pin = scanner.nextInt();

								date = LocalDate.now();
								account_number = customer.chechBalance(pin);
								type = "check balance";
								customer.addTransaction(amount, date, "current place", account_number, "enquiry",
										"balance enqury");
							} else if (option == 4) {

								System.out.println("Enter Account_number");
								pin = scanner.nextInt();
								System.out.println("Enter Amount");
								amount = scanner.nextDouble();

								scanner.nextLine();
								System.out.println("Transaction Place");
								place = scanner.nextLine();
								System.out.println("Reference");
								ref = scanner.nextLine();
								date = LocalDate.now();
								type = "withdrawals";
								account_number = customer.withdraw(pin, amount);
								customer.addTransaction(amount, date, place, account_number, ref, type);

							} else if (option == 5) {
								/// can choose to add the where clause
								System.out.println("Enter Account Number");
								account_number = scanner.nextInt();
								int year = 0, day = 0, month = 0;

								System.out.println("From what date to Print (fig)");
								System.out.println("Year");
								year = scanner.nextInt();
								System.out.println("Month");
								month = scanner.nextInt();
								System.out.println("Day");
								day = scanner.nextInt();

								date = LocalDate.of(year, month, day);

								String printStatement[] = customer.printStatement(account_number, date);
								for (int i = 0; i < printStatement.length; i++) {
									System.out.println(printStatement[i]);
									if (printStatement[i].equals(null)) {
										break;
									}
								}

							}

						} // if statmt

					} // while checking login
					if (rs.next() == false) {
						System.out.println("username or password is incorrect!");
					}
				} catch (Exception e) {

					// TODO: handle exception
				}
				break mainSwith;
			case 3:
				System.exit(0);
			} // end of Switch Statement

		} // end of employee Stuff

	}

}
