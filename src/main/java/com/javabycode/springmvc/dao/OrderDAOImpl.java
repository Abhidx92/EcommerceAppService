package com.javabycode.springmvc.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javabycode.springmvc.model.CartInfo;
import com.javabycode.springmvc.model.CartLineInfo;
import com.javabycode.springmvc.model.CustomerInfo;
import com.javabycode.springmvc.model.Order;
import com.javabycode.springmvc.model.OrderDetail;
import com.javabycode.springmvc.model.OrderDetailInfo;
import com.javabycode.springmvc.model.OrderInfo;
import com.javabycode.springmvc.model.PaginationResult;
import com.javabycode.springmvc.model.Product;

@Repository("OrderDAO")
@Transactional
public class OrderDAOImpl extends AbstractDao<Serializable, Order> implements OrderDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    @Autowired
    private ProductDAO productDAO;
 
    
 
    @Override
    public void saveOrder(Order order) {
    	Query query = getSession().createSQLQuery("insert into orders (customerId, productCode, AMOUNT) values (:customerId, :productCode, :amount)");
		query.setInteger("customerId", order.getCustomerId());
		query. setString("productCode", order.getProductCode());
		query. setDouble("amount", order.getAmount());
		query.executeUpdate();
    }
 
    
    public Order findOrder(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Order.class);
        crit.add(Restrictions.eq("id", orderId));
        return (Order) crit.uniqueResult();
    }
 
    
    @Override
    public List<Order> listOrderDetailInfos(int userId) {
    	 Session session = sessionFactory.getCurrentSession();
         Criteria crit = session.createCriteria(Order.class);
         crit.add(Restrictions.eq("userId",userId ));
         return crit.list();
    }
 
}
