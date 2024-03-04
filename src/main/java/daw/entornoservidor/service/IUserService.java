package daw.entornoservidor.service;


import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserCartDTO;
import daw.entornoservidor.model.DTO.UserDTO;

public interface IUserService {

	public UserDTO save(User user);
	
	public UserCartDTO cart(String username);
	
	public UserCartDTO addToCart(String username, Integer id);
	
}
