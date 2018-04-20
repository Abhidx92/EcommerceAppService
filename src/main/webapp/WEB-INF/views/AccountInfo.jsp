<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Account Info</title>
 
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet">
 
</head>
<body>
 
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Account Info</div>
 
   <div class="account-container">
 
<input type="hidden" name="user" value="${user}">
       <ul>
       <li><h2>Welcome</h2></li>
           <li><h2>${user.username}</h2></li>
     </ul>
 
   </div>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>