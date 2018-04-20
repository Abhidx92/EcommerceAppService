package com.javabycode.springmvc.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javabycode.springmvc.model.ShoppingCart;
import com.javabycode.springmvc.model.Student;

@Repository("ShoppingDAO")
@Transactional
public class ShoppingDaoImpl extends AbstractDao<Serializable, ShoppingCart>implements ShoppingDAO {

	@Override
	public int add(ShoppingCart shoppingCart) {
		ShoppingCart sCart =  new ShoppingCart();
		sCart = getCartEntry(shoppingCart);
		if(null != sCart )
		{
			int currQuantity = sCart.getQuantity() + 1;
			shoppingCart.setQuantity(currQuantity);
			Query query = getSession().createSQLQuery("update shoppingcart set quantity = :quantity where customerId = :customerId AND productCode = :productCode");
			query.setInteger("quantity", currQuantity);
			query.setInteger("customerId", sCart.getCustomerId());
			query. setString("productCode", sCart.getProductCode());
			query.executeUpdate();
			return sCart.getQuantity();
		}
		Query query = getSession().createSQLQuery("insert into shoppingcart (customerId, productCode,quantity,productName,amount) values (:customerId, :productCode,:quantity, :productName,:amount)");
		query.setInteger("customerId", shoppingCart.getCustomerId());
		query. setString("productCode", shoppingCart.getProductCode());
		query.setInteger("quantity", shoppingCart.getQuantity()+1);
		query.setString("productName", shoppingCart.getProductName());
		query.setDouble("amount", shoppingCart.getAmount());
		query.executeUpdate();
		//persist(shoppingCart);
		return shoppingCart.getQuantity();
		
	}

	@Override
	public void remove(ShoppingCart shoppingCart) {
		ShoppingCart scart = getCartEntry(shoppingCart);
		if(scart.getQuantity() > 1)
		{
			Query query = getSession().createSQLQuery("update shoppingCart set quantity = :quantity where customerId = :customerId AND productCode = :productCode");
			query.setInteger("quantity", scart.getQuantity() -1);
			query.setInteger("customerId", scart.getCustomerId());
			query. setString("productCode", scart.getProductCode());
			query.executeUpdate();	
			
		}
		else
		{
		Query query = getSession().createSQLQuery("delete from shoppingCart where customerId = :customerId AND productCode=:productCode");
		query.setInteger("customerId", shoppingCart.getCustomerId());
		query.setString("productCode", shoppingCart.getProductCode());
		query.executeUpdate();
		}
	}

	@Override
	public ShoppingCart getCartEntry(ShoppingCart shoppingCart){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("customerId", shoppingCart.getCustomerId()));
		criteria.add(Restrictions.eq("productCode", shoppingCart.getProductCode()));
		return (ShoppingCart) criteria.uniqueResult();

		
	}

	@Override
	public List<ShoppingCart> getShoppingCart(int  customerId) {
		/*Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("customerId", customerId));*/
		List<ShoppingCart> cartList = (List<ShoppingCart>)getSession().createQuery("select sc from ShoppingCart sc where sc.customerId= "+ customerId + "").list();
		System.out.println("cartList..............." + cartList.toString());
		for(ShoppingCart cart: cartList){
			
		}
		return cartList;

	}

}
