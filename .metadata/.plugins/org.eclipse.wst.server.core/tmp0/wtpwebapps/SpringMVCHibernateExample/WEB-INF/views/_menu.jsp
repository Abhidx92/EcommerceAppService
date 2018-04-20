<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menu-container">
  
   <a href="<c:url value='/home' />">home</a>
   |
  <a href="<c:url value='/productList' />">Product List</a>
     
   |
   <a href="<c:url value='/shoppingCart' />">My Cart</a>
      
   |
   
    <a href="<c:url value='/orderList' />">Order List</a>
     
     |
   
  <a href="<c:url value='/trackOrder' />">Track Order</a>
    
         |

  
</div>