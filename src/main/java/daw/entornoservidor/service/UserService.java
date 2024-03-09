package daw.entornoservidor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daw.entornoservidor.model.Role;
import daw.entornoservidor.model.User;

import daw.entornoservidor.model.DTO.UserDTO;

import daw.entornoservidor.model.DTO.mapper.DTOMapperUser;

import daw.entornoservidor.repository.RoleRepository;
import daw.entornoservidor.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public UserDTO save(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Optional<Role> optional = roleRepository.findByName("ROLE_USER");
		
		List<Role> roles = new ArrayList<>();
		
		if (optional.isPresent()) {
			
			roles.add(optional.orElseThrow());
		}
			
		user.setRoles(roles);
		
		
		return DTOMapperUser.builder().setUser(userRepository.save(user)).build();
	}

	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
	}
}
