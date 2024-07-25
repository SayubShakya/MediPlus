 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link rel="stylesheet" href="FirstPage.css">

</head>
<body>
<form action='LoginServlet' method='post'>

    <div class="container">
     		 <h1>Select Role</h1>
     		 <div class = "links">
            <p><button  class ="hulu1" type="submit" name="role" value="admin">Admin</button></p>
            </div>
            
            <div class = "links">
            <p><button class ="hulu2" type="submit" name="role" value="user">User</button></p>
        </div>
        </div>
       
</form>
</body>
</html>
