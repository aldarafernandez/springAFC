package daw.entornoservidor.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.entornoservidor.model.User;
import daw.entornoservidor.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/new")
	public User newUser(@RequestBody User user) {
		
		return userRepository.save(user);
	}
}
