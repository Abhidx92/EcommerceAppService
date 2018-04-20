<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="<c:url value="/resources/css/login.css" />" rel="stylesheet">
<!-- <script src="~/Scripts/jquery-1.10.2.js"></script> -->
<script type="text/javascript">
    function CheckGoogleLogin() {
    	
   	
   window.location.href = "https://authenticatemeapi.azurewebsites.net/api/Account/ExternalLogin?provider=Google&response_type=token&client_id=self&redirect_uri=http://localhost:8080/accountInfo";
            
    
    }

</script>
</head>
<body>

<div class="jumbotron">

</div>

<div class="row">
    <div class="col-md-4">
     <h2>Login</h2>
        <form action = "<c:url value='/loginUser' />" method = "GET">
         <div>UserName :<input type="text" name="username" /></div>
            <div> Password :<input type="text" name="password"/></div>
            <div><input type="submit" value="submit">
        </form>
     <% 
     String username=request.getParameter("username");
     String password=request.getParameter("password");
	session.setAttribute("username",username); 
	session.setAttribute("password",password); 
	%>  
       
        <div>
            <table>
                <tr>
                    <td>
                        <input type="button" value="Login with Google" id="btnGoogle" onclick="CheckGoogleLogin()" />
                    </td>
                </tr>
            </table>
        </div>


    </div>

</div>
</body>
</html>