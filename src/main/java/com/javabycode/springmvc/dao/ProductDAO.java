package com.javabycode.springmvc.dao;

import java.util.List;

import com.javabycode.springmvc.model.PaginationResult;
import com.javabycode.springmvc.model.Product;
import com.javabycode.springmvc.model.ProductInfo;

public interface ProductDAO {
 
    public Product findProduct(String code);
    
    public ProductInfo findProductInfo(String code) ;
  
    public List<ProductInfo> queryProducts();
 
    
}