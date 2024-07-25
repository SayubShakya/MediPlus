<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
<link rel="stylesheet" href="Login.css">
</head>
<body>
<% 
    Cookie[] cookies = request.getCookies();
    String user = "";
    String pass = "";
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("user".equals(cookie.getName())) {
                user = cookie.getValue();
            }
            if ("pass".equals(cookie.getName())) {
                pass = cookie.getValue();
            }
        }
    }
    String error = request.getParameter("error");
%>
<form action='LoginServlet2' method='post'>
    <div class="container">
        <h1>Admin Login</h1>
        <p>Username: <input class="items" type='text' name='txtUser' value="<%= user %>"></p>
        <p>Password: <input class="items" type='password' name='txtPass' value="<%= pass %>"></p>
        <p><input class="input" type='checkbox' name='chkSave' value='yes' <% if (!user.isEmpty()) { %>checked<% } %>>Remember user</p>
        <p><input class="Button" type='submit' value='Login'></p>
        <% if ("true".equals(error)) { %>
            <p style="color:red;">Invalid username or password!</p>
        <% } %>
    </div>
</form>
</body>
</html>
