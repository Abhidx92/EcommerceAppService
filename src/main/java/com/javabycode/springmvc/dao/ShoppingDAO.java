package com.javabycode.springmvc.dao;

import java.util.List;

import com.javabycode.springmvc.model.ShoppingCart;

public interface ShoppingDAO {

	int add(ShoppingCart shoppingCart);
	void remove(ShoppingCart shoppingCart);
	List<ShoppingCart> getShoppingCart(int customerId);
	ShoppingCart getCartEntry(ShoppingCart shoppingCart);
}
