<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
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
    
    label{
    	text-alin: left;
    }
    
</style>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark">
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Produtos</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${produto != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${produto == null}">
					<form action="insert" method="post">
				</c:if>

					<caption>
						<h2>
							<c:if test="${produto != null}">
	            			Editar Produto
	            		</c:if>
							<c:if test="${produto == null}">
	            			Adicionar Novo Produto
	            		</c:if>
						</h2>
					</caption>

					<c:if test="${produto != null}">
						<input type="hidden" name="id" value="<c:out value='${produto.id}' />" />
					</c:if>

					<fieldset class="form-group">
						<label>Name do produto</label> <input type="text"
							value="<c:out value='${produto.name}' />" class="form-control"
							name="name" required="required">
					</fieldset>
	
					<fieldset class="form-group">
						<label>Email</label> <input type="text"
							value="<c:out value='${produto.email}' />" class="form-control"
							name="email">
					</fieldset>
	
					<fieldset class="form-group">
						<label>Fornecedor</label> <input type="text"
							value="<c:out value='${produto.fornecedor}' />" class="form-control"
							name="fornecedor">
					</fieldset>
	
					<button type="submit" class="btn btn-success">Salvar</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>