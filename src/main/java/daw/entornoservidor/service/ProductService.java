package daw.entornoservidor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import daw.entornoservidor.model.Product;
import daw.entornoservidor.repository.ProductRepository;

@Service
public class ProductService implements IProductService{

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional
	public List<Product> findAll() {
		
		return productRepository.findAll();
	}

	@Override
	@Transactional
	public Product findById(Integer id) {
		
		Optional<Product> optional = productRepository.findById(id);
		
		if (optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}
}
