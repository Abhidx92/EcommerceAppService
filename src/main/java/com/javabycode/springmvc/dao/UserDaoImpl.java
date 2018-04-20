package com.javabycode.springmvc.dao;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javabycode.springmvc.model.Student;
import com.javabycode.springmvc.model.User;

@Repository("UserDAO")
@Transactional
public class UserDaoImpl extends AbstractDao<Serializable, User> implements UserDAO{

	@Override
	public User  getCustomerId(int userId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("userId", userId));
		return (User) criteria.uniqueResult();
	}
	
	
}
