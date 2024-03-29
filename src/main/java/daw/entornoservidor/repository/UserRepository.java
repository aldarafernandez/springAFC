package daw.entornoservidor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import daw.entornoservidor.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public Optional<User> findByUsername(String username);
}
