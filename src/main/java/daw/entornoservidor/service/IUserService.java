package daw.entornoservidor.service;


import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserDTO;

public interface IUserService {

	public UserDTO save(User user);
	
}
