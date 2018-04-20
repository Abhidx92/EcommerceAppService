package com.javabycode.springmvc.model;

import java.util.List;

public class ShoppingCartList {

	List<ShoppingCart> cartList;

	public List<ShoppingCart> getCartList() {
		return cartList;
	}

	public void setCartList(List<ShoppingCart> cartList) {
		this.cartList = cartList;
	}

	@Override
	public String toString() {
		return "ShoppingCartList [cartList=" + cartList + "]";
	}
	
	
}
