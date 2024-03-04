package daw.entornoservidor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daw.entornoservidor.model.CartProduct;
import daw.entornoservidor.model.Role;
import daw.entornoservidor.model.User;
import daw.entornoservidor.model.DTO.UserCartDTO;
import daw.entornoservidor.model.DTO.UserDTO;
import daw.entornoservidor.model.DTO.mapper.DTOMapperCart;
import daw.entornoservidor.model.DTO.mapper.DTOMapperUser;
import daw.entornoservidor.repository.ProductRepository;
import daw.entornoservidor.repository.RoleRepository;
import daw.entornoservidor.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
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
	public UserCartDTO cart(String username) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isPresent()) {
			
			return DTOMapperCart.builder().setUser(user.orElseThrow()).build();
		}
		
		return null;
	}

	@Override
	public UserCartDTO addToCart(String username, Integer id) {
		
		Optional<User> user = userRepository.findByUsername(username);
		
		if (user.isPresent()) {
			
			user.orElseThrow().getCart().getCartProducts().add(new CartProduct(productRepository.findById(id).orElseThrow(), 1));
			
			
			return DTOMapperCart.builder().setUser(user.orElseThrow()).build();
		}
		
		return null;
	}
}
