package com.javabycode.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {
int customerId;
String productCode;
int quantity;
String productName;
double amount;




public ShoppingCart() {
	super();
}
public ShoppingCart(int customerId, String productCode, int quantity, String productName) {
	super();
	this.customerId = customerId;
	this.productCode = productCode;
	this.quantity = quantity;
	this.productName = productName;
}
@Column(name = "customerId")
public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
@Id
@Column(name = "productCode")
public String getProductCode() {
	return productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
@Column(name = "quantity")
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}

@Column(name = "productName")
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}


@Override
public String toString() {
	return "ShoppingCart [customerId=" + customerId + ", productCode=" + productCode + ", quantity=" + quantity
			+ ", productName=" + productName + "]";
}


@Column(name = "amount")
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}




}
