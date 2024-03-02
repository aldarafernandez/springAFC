package daw.entornoservidor.auth.services;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import daw.entornoservidor.repository.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<daw.entornoservidor.model.User> optional = userRepository.findByUsername(username);
		
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(String.format("El usuario %s no existe", username));
		
		}
		
		daw.entornoservidor.model.User user = optional.orElseThrow();
		
		List<GrantedAuthority> authorities = user.getRoles()
												.stream()
												.map(role -> new SimpleGrantedAuthority(role.getName()))
												.collect(Collectors.toList());
				
		return new User(user.getUsername(), 
				user.getPassword(), 
				true, 
				true, 
				true, 
				true, 
				authorities);
	}

}
