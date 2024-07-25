<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.SQLException"%>
<%
    ResultSet results = (ResultSet) request.getAttribute("results");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .search-results {
            padding: 20px;
            max-width: 1200px;
            margin: 30px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .section-title {
            text-align: center;
            margin-bottom: 20px;
        }
        .section-title h2 {
            color: #333;
            margin: 0;
        }
        .section-title img {
            margin-top: 10px;
            width: 100px;
        }
        table.results-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table.results-table th, table.results-table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        table.results-table th {
            background-color: #f2f2f2;
            color: #333;
        }
        table.results-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            color: #fff;
            font-weight: bold;
        }
        .edit-btn {
            background-color: #007bff;
        }
        .edit-btn:hover {
            background-color: #0056b3;
        }
        .delete-btn {
            background-color: #dc3545;
        }
        .delete-btn:hover {
            background-color: #c82333;
        }
        .export-btn {
            background-color: #28a745;
        }
        .export-btn:hover {
            background-color: #218838;
        }
        .no-results {
            text-align: center;
            color: #777;
        }
        .actions-container {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <section class="search-results">
        <div class="container">
            <div class="section-title">
                <h2>Search Results</h2>
                <img src="img/section-img.png" alt="Section Image">
            </div>
            <div class="actions-container">
                <form action="ExportAllServlet" method="post" style="display: inline-block;">
                    <button type="submit" class="btn export-btn">Export All</button>
                </form>
            </div>
            <%
                if (results == null) {
            %>
            <p class="no-results">No results found.</p>
            <%
                } else {
                    boolean hasResults = false;
            %>
            <table class="results-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Department</th>
                        <th>Doctor</th>
                        <th>Date</th>
                        <th>Message</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        try {
                            while (results.next()) {
                                hasResults = true;
                    %>
                    <tr>
                        <td><%= results.getInt("id") %></td>
                        <td><%= results.getString("name") %></td>
                        <td><%= results.getString("email") %></td>
                        <td><%= results.getString("phone") %></td>
                        <td><%= results.getString("department") %></td>
                        <td><%= results.getString("doctor") %></td>
                        <td><%= results.getString("date") %></td>
                        <td><%= results.getString("message") %></td>
                        <td>
                            <form action="EditAppointmentServlet" method="post" style="display: inline-block;">
                                <input type="hidden" name="id" value="<%= results.getInt("id") %>">
                                <button type="submit" class="btn edit-btn">Edit</button>
                            </form>
                            <form action="DeleteAppointmentservlet" method="post" style="display: inline-block;">
                                <input type="hidden" name="id" value="<%= results.getInt("id") %>">
                                <button type="submit" class="btn delete-btn">Delete</button>
                            </form>
                            <form action="ExportIndividualServlet" method="post" style="display: inline-block;">
                                <input type="hidden" name="id" value="<%= results.getInt("id") %>">
                                <button type="submit" class="btn export-btn">Export</button>
                            </form>
                        </td>
                    </tr>
                    <%
                            }
                            if (!hasResults) {
                    %>
                    <tr>
                        <td colspan="9" class="no-results">No matching records found.</td>
                    </tr>
                    <%
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    %>
                </tbody>
            </table>
            <% } %>
        </div>
    </section>
</body>
</html>
