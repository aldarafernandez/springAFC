package daw.entornoservidor.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserDTO;
import daw.entornoservidor.service.IUserService;


@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private IUserService userService;

	@PostMapping("/new")
	public UserDTO newUser(@RequestBody User user) {
		
		return userService.save(user);
	}
	
	
	@GetMapping("/users")
	public List<User> all(){
		
		return userService.findAll();
	}
}
