package daw.entornoservidor.service;


import java.util.List;

import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserDTO;

public interface IUserService {

	public UserDTO save(User user);
	
	public List<User> findAll();
	
}
