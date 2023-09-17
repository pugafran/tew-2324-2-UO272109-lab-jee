<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mi Tienda!</title>
</head>
<body>
	<div class="contenedor-centro">
		<%
			//Comprobamos si existe el objeto "carrito" en sesión.
			//Si no existe, lo creamos vacío. Será de tipo HashMap
			@SuppressWarnings("unchecked")
			HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
			if (carrito == null) {
				carrito = new HashMap<String, Integer>();
			}

			//Añadimos el producto recibido al carrito de la compra (en caso de que no sea nulo!)
			String producto = request.getParameter("producto");

			if (producto != null && "POST".equalsIgnoreCase(request.getMethod())) {
				Integer cantidad = carrito.get(producto);
				if (cantidad == null)
					cantidad = new Integer(1);
				else
					cantidad = new Integer(cantidad.intValue() + 1);

				carrito.put(producto, cantidad);
				request.getSession().setAttribute("carrito", carrito);

				// Redirigimos al usuario a la misma página para evitar el reenvío del formulario
				response.sendRedirect("tienda.jsp");
				return;
			}
		%>

		<h1>Tienda Virtual</h1>
		<form action="tienda.jsp" method="post">
			<br>
			<table>
				<tr>
					<td>escoja el artículo que desea:</td>
				</tr>
				<tr>
					<td><select name="producto" size="1">
							<option value="010">la trampa</option>
							<option value="345">los pájaros</option>
							<option value="554">matrix reloaded</option>
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
			</c:forEach>
		</ul>
	</div>
</body>
<html>