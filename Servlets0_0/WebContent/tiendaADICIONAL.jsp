<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.tew.beans.Counter"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mi Tienda!</title>
</head>
<body>
	<div class="contenedor-centro">
		<%
			
			Counter contador = (com.tew.beans.Counter)getServletContext().getAttribute("counter");
		    if (contador == null) {
		        contador = new Counter();
		        getServletContext().setAttribute("counter", contador);
		    }

		%>

		<h1>Tienda Virtual</h1>
		<form action='/Servlets0_0/CarritoCompraServletJSP' method="post">
			<br>
			<table>
				<tr>
					<td>escoja el artículo que desea:</td>
				</tr>
				<tr>
					<td><select name="producto" size="1">
						<option value="667">El señor de los anillos</option>
						<option value="678">Guerra y Paz</option>
						<option value="689">El código Da Vinci</option>
						<option value="690">El Alquimista</option>
						<option value="701">Los juegos del hambre</option>
						<option value="712">Crepúsculo</option>
						<option value="723">Harry Potter y la piedra filosofal</option>
						<option value="734">Percy Jackson y el ladrón del rayo</option>
						<option value="745">Divergente</option>
						<option value="756">El nombre del viento</option>

					</select></td>
				</tr>
				<tr>
					<td><input type="submit" value="añadir al carrito...">
					</td>
				</tr>
			</table>
		</form>
		
		

		<h2>Carrito de la compra</h2>

		<ul>
			<c:forEach var="entry" items="${carrito}">
				<li><c:out
						value="Del producto ${entry.key}, ${entry.value} unidades" /></li>
					<p>Valor del contador: <%= contador.getIncrementedValue() %></p> 
			</c:forEach>
		</ul>
		
		
	</div>
</body>
<html>