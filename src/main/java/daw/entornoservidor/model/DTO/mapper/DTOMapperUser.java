package daw.entornoservidor.model.DTO.mapper;

import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserDTO;

public class DTOMapperUser {
	
	private User user;

	
	
	private DTOMapperUser() {
	}
	
	
	public static DTOMapperUser builder() {
		return new DTOMapperUser();
	}

	public DTOMapperUser setUser (User user) {
		this.user = user;
		return this;
	}
	
	public UserDTO build() {
		if (user == null) {
			throw new RuntimeException();
		}
		return new UserDTO(user.getId(), user.getUsername(), user.getName(), user.getSurname(), user.getEmail());
	}
}
