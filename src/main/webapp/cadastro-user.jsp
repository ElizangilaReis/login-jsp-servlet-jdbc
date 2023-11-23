<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        text-align: center;
    }

    .container {
        width: 60%;
        margin: 50px auto;
        text-align: center;
        background-color: #fff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1, h2 {
        color: #333;
    }

    form {
        display: inline-block;
        margin-right: 10px;
    }

    input[type="submit"] {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #0056b3;
    }
    
    
</style>
<title>Insert title here</title>
</head>
<body>
	<div align="center" class="container col-md-5">
		<h1>Cadastro de Novo Usuário</h1>
	    <form action="/login-jsp-servlet-jdbc/CadastroUsuarioServlet" method="post">
	        <label for="username">Username:</label>
	        <input type="text" id="username" name="username" required><br><br>
	         
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" required><br><br>
	        
	        <input type="submit" value="Cadastrar">
	    </form>
	    <div align="center">
	        <p>Já tem uma conta? <a href="login.jsp">Faça login aqui</a>.</p>
	    </div>
	    <div align="center">
	    <p>
	        <% String successMessage = (String) session.getAttribute("successMessage");
	        if (successMessage != null) { %>
	            <span style="color: green;"><%= successMessage %></span>
	        <% } %>
	    </p>
</div>
	 </div>
</body>
</html>