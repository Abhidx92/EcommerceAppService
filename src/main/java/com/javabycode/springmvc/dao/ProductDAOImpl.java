package com.javabycode.springmvc.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javabycode.springmvc.model.PaginationResult;
import com.javabycode.springmvc.model.Product;
import com.javabycode.springmvc.model.ProductInfo;
import com.javabycode.springmvc.model.Student;

@Repository("ProductDAO")
@Transactional
public class ProductDAOImpl extends AbstractDao<Serializable, Product> implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product findProduct(String code) {
		return getByKey(code);
	}

	@Override
	public ProductInfo findProductInfo(String code) {
		Product product = this.findProduct(code);
		if (product == null) {
			return null;
		}
		return new ProductInfo(product.getCode(), product.getName(), product.getPrice());
	}

	/*
	 * @Override public void save(ProductInfo productInfo) { String code =
	 * productInfo.getCode();
	 * 
	 * Product product = null;
	 * 
	 * boolean isNew = false; if (code != null) { product =
	 * this.findProduct(code); } if (product == null) { isNew = true; product =
	 * new Product(); product.setCreateDate(new Date()); }
	 * product.setCode(code); product.setName(productInfo.getName());
	 * product.setPrice(productInfo.getPrice());
	 * 
	 * if (productInfo.getFileData() != null) { byte[] image =
	 * productInfo.getFileData().getBytes(); if (image != null && image.length >
	 * 0) { product.setImage(image); } } if (isNew) {
	 * this.sessionFactory.getCurrentSession().persist(product); } // If error
	 * in DB, Exceptions will be thrown out immediately // Nếu có lỗi tại
	 * DB, ngoại lệ sẽ ném ra ngay lập tức
	 * this.sessionFactory.getCurrentSession().flush(); }
	 */
	@Override
	public List<ProductInfo> queryProducts() {

		Criteria criteria = createEntityCriteria();
		return (List<ProductInfo>) criteria.list();

	}

}
