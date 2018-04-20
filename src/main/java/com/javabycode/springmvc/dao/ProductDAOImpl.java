package com.javabycode.springmvc.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javabycode.springmvc.model.Product;
import com.javabycode.springmvc.model.ProductInfo;
import com.javabycode.springmvc.model.ShoppingCart;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl extends AbstractDao<Serializable, Product> implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product findProduct(String code) {
		/*Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
Product
		return (Product) criteria.uniqueResult();*/
		Product product= new Product();
		/*try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2233775","sql2233775","fX3*lU5%");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from products");  
			while(rs.next())  
			System.out.println("//////////////////" + rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			product.setCode(rs.getString(5));
			product.setName(rs.getString(2));
		
			product.setPrice(rs.getFloat(3));;
			product
			con.close();  
			return product;
			}catch(Exception e){ System.out.println(e);}  
		return product;
			}  */
		// getByKey(code);
		product.setCode("S2");
		product.setName("Core Java");
		product.setPrice(400);
		return product;
	}
	

	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product == null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
	}

	@Override
	public List<ProductInfo> queryProducts() {
		List<ProductInfo> productList = new ArrayList<>();
	
		productList.add(new ProductInfo("S2", "C Sharp", 440));
		/*productList.add(new ProductInfo("S3", "Oracle xml", 300));
		productList.add(new ProductInfo("S4", "Spring Beginner", 200));
		productList.add(new ProductInfo("S5", "Swift Beginner", 100));**/
		
		/*try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2233775","sql2233775","fX3*lU5%");  
			//here sonoo is database name, root is username and password  
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select name,price,code from products");  
			while(rs.next())  
			System.out.println("//////////////////" + rs.getInt(4)+"  "+rs.getString(2)+"  "+rs.getString(3));
			productList.add(new ProductInfo(rs.getString(4), rs.getString(2), rs.getDouble(3)));
			
			con.close();  
			}catch(Exception e){ System.out.println(e);}  */
		System.out.println("PPPPPPPPP" + productList.toString());
		Criteria criteria = createEntityCriteria();
		return productList;
		// return (List<ProductInfo>) criteria.list();

	}

}
