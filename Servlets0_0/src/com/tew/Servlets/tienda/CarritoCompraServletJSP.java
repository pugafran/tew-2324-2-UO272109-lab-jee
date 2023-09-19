package com.tew.Servlets.tienda;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import com.tew.beans.Counter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CarritoCompraServletJSP")
public class CarritoCompraServletJSP extends HttpServlet {
	private static final long serialVersionUID = 3L;

    public CarritoCompraServletJSP() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		
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

		}

		
        RequestDispatcher dispatcher =
        		 getServletContext().getRequestDispatcher("/tiendaADICIONAL.jsp");
        		dispatcher.forward(request, response);

	}
}
