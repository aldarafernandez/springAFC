package daw.entornoservidor.model.DTO.mapper;

import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserCartDTO;


public class DTOMapperCart {

	private User user;

	
	
	private DTOMapperCart() {
	}
	
	
	public static DTOMapperCart builder() {
		return new DTOMapperCart();
	}

	public DTOMapperCart setUser (User user) {
		this.user = user;
		return this;
	}
	
	public UserCartDTO build() {
		if (user == null) {
			throw new RuntimeException();
		}
		return new UserCartDTO(user.getId(), user.getUsername(), user.getCart());
	}
}
