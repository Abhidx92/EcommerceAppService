<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
 <link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
</head>
<body>

   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Product List</div>
 
 
 
   <c:forEach items="${paginationProducts}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
               <li><img class="product-image"
                    src="<c:url value="/img/${prodInfo.name}.jpg"/>?code=${prodInfo.code}" /></li>
               <li>Code: ${prodInfo.code}</li>
               <li>Name: ${prodInfo.name}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></li>
               <li><a
                   href="<c:url value="/buyProduct" />?code=${prodInfo.code}">
                       Buy Now</a></li>

               <input type="hidden" name="user" value="${user}">
                 <li><a style="color:red;"
                     href="<c:url value='/product' />"?code=${prodInfo.code}>
                       Edit Product</a></li>
              
           </ul>
       </div>
 
   </c:forEach>
   <br/>
  
   <jsp:include page="_footer.jsp" />
 
</body>
</html>