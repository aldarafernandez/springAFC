package daw.entornoservidor.model.DTO;

public class UserDTO {

	private Integer id;
	
	private String username;
	
	private String name;
	
	private String surname;
	
	private String email;

	
	
	public UserDTO() {
	}

	public UserDTO(Integer id, String username, String name, String surname, String email) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", name=" + name + ", surname=" + surname + ", email="
				+ email + "]";
	}
	
	
	
}
