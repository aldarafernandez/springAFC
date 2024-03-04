package daw.entornoservidor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartProduct")
public class CartProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	private Integer quantity;

	
	
	public CartProduct() {
	}
	

	public CartProduct(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}


	public CartProduct(Integer id, Product product, Integer quantity) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public Integer getQuantity() {
		return quantity;
	}



	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}



	@Override
	public String toString() {
		return "CartProduct [id=" + id + ", product=" + product + ", quantity=" + quantity + "]";
	}
	
	
	
}
