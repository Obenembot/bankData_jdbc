package person;

public class Person {
	private String name;
	private String surname;
	private long phone_number; //
	private String email_address;
	private String house_address;
	private int id_number;  // PRIMARY KEY

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getHouse_address() {
		return house_address;
	}

	public void setHouse_address(String house_address) {
		this.house_address = house_address;
	}

	public int getId_number() {
		return id_number;
	}

	public void setId_number(int id_number) {
		this.id_number = id_number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email_address == null) ? 0 : email_address.hashCode());
		result = prime * result + ((house_address == null) ? 0 : house_address.hashCode());
		result = prime * result + id_number;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (phone_number ^ (phone_number >>> 32));
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Person other = (Person) obj;
		if (email_address == null) {
			if (other.email_address != null)
				return false;
		} else if (!email_address.equals(other.email_address))
			return false;
		if (house_address == null) {
			if (other.house_address != null)
				return false;
		} else if (!house_address.equals(other.house_address))
			return false;
		if (id_number != other.id_number)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone_number != other.phone_number)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", surname=" + surname + ", phone_number=" + phone_number + ", email_address="
				+ email_address + ", house_address=" + house_address + ", id_number=" + id_number + "]";
	}

}
