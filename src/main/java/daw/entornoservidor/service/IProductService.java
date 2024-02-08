package daw.entornoservidor.service;

import java.util.List;

import daw.entornoservidor.model.Product;

public interface IProductService {

	public List<Product> findAll();
	public Product findById(Integer id);
}
