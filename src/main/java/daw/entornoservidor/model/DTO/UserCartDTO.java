package daw.entornoservidor.model.DTO;

import daw.entornoservidor.model.Cart;

public class UserCartDTO {

	private Integer id;
	
	private String username;
	
	private Cart cart;

	
	
	
	
	public UserCartDTO() {
	}

	public UserCartDTO(Integer id, String username, Cart cart) {
		this.id = id;
		this.username = username;
		this.cart = cart;
	}
	
	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	
	
	
	
	@Override
	public String toString() {
		return "UserCartDTO [id=" + id + ", username=" + username + ", cart=" + cart + "]";
	}
	
	
}
