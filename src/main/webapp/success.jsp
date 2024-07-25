<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Operation Result</title>
    <link rel="stylesheet" href="path/to/your/css/file.css">
</head>
<body>
    <section class="operation-result">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Operation Result</h2>
                    </div>
                    <div class="result-message">
                        <%
                            String message = (String) request.getAttribute("message");
                            if (message != null) {
                        %>
                        <p><%= message %></p>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
