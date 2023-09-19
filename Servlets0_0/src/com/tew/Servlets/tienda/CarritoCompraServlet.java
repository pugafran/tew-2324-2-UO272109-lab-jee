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

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Integer> carrito = (HashMap<String, Integer>) request.getSession().getAttribute("carrito");

        String productoSeleccionado = request.getParameter("producto");

        carrito.put(productoSeleccionado, carrito.getOrDefault(productoSeleccionado, 0) + 1);

        request.getSession().setAttribute("carrito", carrito);

        request.getRequestDispatcher("CarritoCompraVistaServlet").forward(request, response);
	}
}
