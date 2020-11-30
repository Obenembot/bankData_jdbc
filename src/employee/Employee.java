package employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;

import person.Person;

public class Employee extends Person {

	private String user_name;
	private String password;
	private String employee_position;
	private LocalDate date;

	public Employee(String user_name, String password, String employee_position, LocalDate date) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.employee_position = employee_position;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployee_position() {
		return employee_position;
	}

	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((employee_position == null) ? 0 : employee_position.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
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
		Employee other = (Employee) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (employee_position == null) {
			if (other.employee_position != null)
				return false;
		} else if (!employee_position.equals(other.employee_position))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}

	public Employee(String user_name, String password, String employee_position) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.employee_position = employee_position;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [user_name=" + user_name + ", password=" + password + ", employee_position="
				+ employee_position + ", date=" + date + " " + super.toString() + "]";
	}

	/*
	 * private String name; private String surname; private long phone_number; //
	 * private String email_address; private String house_address; private int
	 * id_number;
	 */
	public void registerEmployee(String name, String surname, long phone_number, String email, String house_address,
			int id_number, String user_name, String password, String employee_position, LocalDate date) {

		this.setName(name);
		this.setSurname(surname);
		this.setPhone_number(phone_number);
		this.setEmail_address(email);
		this.setHouse_address(house_address);
		this.setId_number(id_number);
		this.setUser_name(user_name);
		this.setPassword(password);
		this.setEmployee_position(employee_position);
		this.setDate(date);
	}

	public void addToEmployee(int id_number, String name, String surname, long phone_number, String email,
			String house_address, LocalDate date, String user_name, String password, String position) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_system_db", "root",
					"pvi@2019");
			Statement statement = connection.createStatement();

			statement.executeUpdate(
					"INSERT INTO employee (id_number, name, surname, house_address, email_address, phone_number,date,"
							+ " user_name, password, position )" + "VALUES(" + id_number + ",'" + name + "','"
							+ surname + "','" + house_address + "','" + email + "'," + phone_number + ",'" + date
							+ "','" + user_name + "','" + password + "','" + position + "')");
		} catch (Exception e) {
			System.out.println(e.getMessage());

			// TODO: handle exception
		}
	}
}
