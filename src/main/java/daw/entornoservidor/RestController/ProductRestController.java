package daw.entornoservidor.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import daw.entornoservidor.model.Product;
import daw.entornoservidor.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

	@Autowired
	IProductService productService;
	
	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/all")
	public List<Product> findAll(){
		
		return productService.findAll();
	}
	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping("/{id}")
	public Product findAll(@PathVariable ("id") Integer id){
		
		return productService.findById(id);
	}
	
}
