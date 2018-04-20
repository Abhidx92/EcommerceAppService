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
  
   <div class="page-title">Order List</div>
 
  
   <table border="1" style="width:100%">
      
       <c:forEach items="${orderList}" var="orderInfo">
           <tr>
                        <td>
                  <fmt:formatDate value="${orderInfo.orderDate}" pattern="dd-MM-yyyy HH:mm"/>
               </td>
          
               <td>${orderInfo.productCode}</td>
              
               <td style="color:red;">
                  <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
               </td>
               
           </tr>
       </c:forEach>
   </table>
  
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>