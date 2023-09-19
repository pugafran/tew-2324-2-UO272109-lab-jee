package com.tew.Servlets.tienda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CarritoCompraServlet")
public class CarritoCompraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CarritoCompraServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");
		
		if(carrito == null) {
			carrito = new HashMap<>();
			request.getSession().setAttribute("carrito", carrito);
		}

		PrintWriter out = response.getWriter();

		out.println("<HTML>");
		out.println("<HEAD><TITLE>Carrito de Compras</TITLE></HEAD>");
		out.println("<BODY>");

		out.write("<form method='post'>");
		out.write("<select name='producto'>");
		out.write("<option value='678'>Cien años de soledad</option>");
		out.write("<option value='791'>Don Quijote de la Mancha</option>");
		out.write("<option value='802'>El arte de la guerra</option>");
		out.write("<option value='915'>1984</option>");
		out.write("<option value='926'>El Gran Gatsby</option>");
		out.write("<option value='1040'>Orgullo y prejuicio</option>");
		out.write("<option value='1151'>La odisea</option>");
		out.write("<option value='1262'>Un mundo feliz</option>");
		out.write("<option value='1373'>Moby Dick</option>");
		out.write("<option value='1484'>To Kill a Mockingbird (Matar a un ruiseñor)</option>");
		// Añade más productos aquí
		out.write("</select>");
		out.write("<input type='submit' value='Agregar al carrito'>");
		out.write("</form>");

		out.write("<h2>Estado del carrito:</h2>");
		for(String key : carrito.keySet()) {
			out.write("Producto " + key + ": " + carrito.get(key) + " unidades<br>");
		}

		out.println("</BODY></HTML>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");

		String productoSeleccionado = request.getParameter("producto");

		carrito.put(productoSeleccionado, carrito.getOrDefault(productoSeleccionado, 0) + 1);
		
		request.getSession().setAttribute("carrito", carrito);

		doGet(request, response);
	}
}
