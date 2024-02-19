package daw.entornoservidor.model;




import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany
	@JoinTable(name = "cartProducts")
	private List<CartProduct> cartProducts = new ArrayList<>();

	
	
	public Cart() {
	}



	public Cart(Integer id, List<CartProduct> cartProducts) {
		this.id = id;
		this.cartProducts = cartProducts;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public List<CartProduct> getCartProducts() {
		return cartProducts;
	}



	public void setCartProducts(List<CartProduct> cartProducts) {
		this.cartProducts = cartProducts;
	}



	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartProducts=" + cartProducts + "]";
	}
	
	
	



}