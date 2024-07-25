<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="pkg1.Appointment" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Edit Appointment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .edit-appointment {
            padding: 20px;
            max-width: 800px;
            margin: 30px auto;
            background: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        .container {
            padding: 0 20px;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group input[type="date"] {
            padding: 8px;
        }
        .form-group textarea {
            resize: vertical;
        }
        .form-group button {
            background-color: #28a745;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
        }
        .form-group button:hover {
            background-color: #218838;
        }
        .error-message {
            color: #d9534f;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <section class="edit-appointment">
        <div class="container">
            <h2>Edit Appointment</h2>
            <%
                Appointment appointment = (Appointment) request.getAttribute("appointment");
                if (appointment != null) {
                    int id = appointment.getId();
                    String name = appointment.getName();
                    String email = appointment.getEmail();
                    String phone = appointment.getPhone();
                    String department = appointment.getDepartment();
                    String doctor = appointment.getDoctor();
                    String date = appointment.getDate();
                    String message = appointment.getMessage();
            %>
            <form action="UpdateAppointmentServlet" method="post">
                <input type="hidden" name="id" value="<%= id %>">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="<%= name %>" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%= email %>" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" value="<%= phone %>" required>
                </div>
                
               <div class="form-group">
    <label for="department">Department:</label>
    <select name="department" id="department" required>
        <option value="">Select Department</option>
        <option value="Cardiac Clinic">Cardiac Clinic</option>
        <option value="Neurology">Neurology</option>
        <option value="Dentistry">Dentistry</option>
        <option value="Gastroenterology">Gastroenterology</option>
    </select>
</div>

                                   <div class="form-group">
                                    <label for="department">Department:</label>
                                    <select name="doctor" required>
                                        <option value="">Select Doctor</option>
                                        <option value="Dr. Akther Hossain">Dr. Akther Hossain</option>
                                        <option value="Dr. Dery Alex">Dr. Dery Alex</option>
                                        <option value="Dr. Jovis Karon">Dr. Jovis Karon</option>
                                    </select>
                                </div>
                            
                            
                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="date" id="date" name="date" value="<%= date %>" required>
                </div>
                <div class="form-group">
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="4" required><%= message %></textarea>
                </div>
                <div class="form-group">
                    <button type="submit">Update Appointment</button>
                </div>
            </form>
            <% } else { %>
            <p class="error-message">No appointment data available.</p>
            <% } %>
        </div>
    </section>
</body>
</html>
