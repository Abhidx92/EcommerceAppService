<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Shopping Cart</title>
 
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
 
</head>
<body>
   <jsp:include page="_header.jsp" />
  
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">My Cart</div>
 
 <input type="hidden" name="user" value="${user}">
  
       <form:form method="POST" modelAttribute="cartForm"
           action="<c:url value='/shoppingCart' />">
 
           <c:forEach items="${cartList}" var="cart"
               varStatus="varStatus">
               <div class="product-preview-container">
                   <ul>
                      <li><img class="product-image"
                    src="<c:url value="/img/${cart.productName}.jpg"/>?code=${prodInfo.code}" /></li> 
                       <li>Name: ${cart.productName}</li>
                       <li>Price: ${cart.amount}<span class="price">
                      
                      
                        
                       </span></li>
              
                       <li><a
                            href="<c:url value="/shoppingCartRemoveProduct" />?code=${cart.productCode}">
                               Delete </a></li>
                   </ul>
               </div>
           </c:forEach>
           <div style="clear: both"></div>
           <input class="button-update-sc" type="submit" value="Update Quantity" />
           
           <a class="navi-item"
               href="<c:url value="/confirmOrder" />">Continue
               Buy</a>
       </form:form>
 
 

 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>