package daw.entornoservidor.auth.filters;

import static daw.entornoservidor.auth.TokenJwtConfig.SECRET_KEY;
import static daw.entornoservidor.auth.TokenJwtConfig.PREFIX_TOKEN;
import static daw.entornoservidor.auth.TokenJwtConfig.HEADER_AUTHORIZATION;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import daw.entornoservidor.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
			throws AuthenticationException{
		
		User user = null;
		String username = null;
		String password = null;
		
		
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			username = user.getUsername();
			password = user.getPassword();
			
			logger.info("Username: " + username);
			logger.info("Password: " + password);
			
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
			
		return authenticationManager.authenticate(authToken);
		
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) 
			throws IOException, ServletException{
		
		String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername(); 
		
		Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();
		
		Boolean isAdmin = roles.stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
		
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		claims.put("isAdmin", isAdmin);
		
		String token = Jwts.builder()
						.setSubject(username)
						.signWith(SECRET_KEY)
						.setIssuedAt(new Date())
						.setExpiration(new Date(System.currentTimeMillis() + 3600000))
						.compact();
		
		response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("username", username);
		body.put("message", String.format("%s INICIÓ SESIÓN", username));
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");
		
	}
	
	
	@Override
	public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) 
			throws IOException, ServletException{
		
		Map<String, Object> body = new HashMap<String, Object>();
		
		body.put("message", "ERROR");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
		
	}
}
