package com.javabycode.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class CartInfo1 {


	   private int orderNum;

	   private CustomerInfo1 customerInfo;

	   private final List<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();

	   public CartInfo1() {

	   }

	   public int getOrderNum() {
	       return orderNum;
	   }

	   public void setOrderNum(int orderNum) {
	       this.orderNum = orderNum;
	   }

	   public CustomerInfo1 getCustomerInfo() {
	       return customerInfo;
	   }

	   public void setCustomerInfo(CustomerInfo1 customerInfo) {
	       this.customerInfo = customerInfo;
	   }

	   public List<CartLineInfo> getCartLines() {
	       return this.cartLines;
	   }

	   private CartLineInfo findLineByCode(String code) {
	       for (CartLineInfo line : this.cartLines) {
	           if (line.getProductInfo().getCode().equals(code)) {
	               return line;
	           }
	       }
	       return null;
	   }

	   public void addProduct(ProductInfo productInfo, int quantity) {
	     // add in oredrDetails, customerid
	   }

	   public void validate() {

	   }

	   public void updateProduct(String code, int quantity) {
	       CartLineInfo line = this.findLineByCode(code);

	       if (line != null) {
	           if (quantity <= 0) {
	               this.cartLines.remove(line);
	           } else {
	               line.setQuantity(quantity);
	           }
	       }
	   }

	   public void removeProduct(ProductInfo productInfo) {
	       CartLineInfo line = this.findLineByCode(productInfo.getCode());
	       if (line != null) {
	           this.cartLines.remove(line);
	       }
	   }

	   public boolean isEmpty() {
	       return this.cartLines.isEmpty();
	   }

	   public boolean isValidCustomer() {
	       return this.customerInfo != null && this.customerInfo.isValid();
	   }

	   public int getQuantityTotal() {
	       int quantity = 0;
	       for (CartLineInfo line : this.cartLines) {
	           quantity += line.getQuantity();
	       }
	       return quantity;
	   }

	   public double getAmountTotal() {
	       double total = 0;
	       for (CartLineInfo line : this.cartLines) {
	           total += line.getAmount();
	       }
	       return total;
	   }

	   public void updateQuantity(CartInfo1 cartForm) {
	       if (cartForm != null) {
	           List<CartLineInfo> lines = cartForm.getCartLines();
	           for (CartLineInfo line : lines) {
	               this.updateProduct(line.getProductInfo().getCode(), line.getQuantity());
	           }
	       }

	   }

}
