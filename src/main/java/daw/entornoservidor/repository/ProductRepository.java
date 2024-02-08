package daw.entornoservidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import daw.entornoservidor.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
