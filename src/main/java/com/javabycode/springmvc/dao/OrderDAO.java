package com.javabycode.springmvc.dao;

import java.util.List;

import com.javabycode.springmvc.model.CartInfo;
import com.javabycode.springmvc.model.Order;
import com.javabycode.springmvc.model.OrderDetailInfo;

public interface OrderDAO {

	public void saveOrder(Order order);

	public List<Order> listOrderDetailInfos(int userId);

}
