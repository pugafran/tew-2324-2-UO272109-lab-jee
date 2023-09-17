package com.tew.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolaMundoServlet
 */
@WebServlet("/HolaMundoServlet")
public class HolaMundoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HolaMundoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		/*
    		 System.out.println("AAAAAAAA");
    		 response.setCharacterEncoding("UTF-8");
    		 response.setContentType("text/html");
    		 PrintWriter out = response.getWriter();
    		 out.println("<HTML>");
    		 out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
    		 out.println("<BODY>");
    		 out.println("Bienvenido a mi primera página web!");
    		 out.println("</BODY></HTML>");

		 */
		

		PrintWriter out = response.getWriter();
		
		String nombre = (String) request.getParameter("NombreUsuario");

		Vector listado = (Vector)request.getSession().getAttribute("listado");
		
		Integer contador= (Integer) getServletContext().getAttribute("contador");
		if ( contador == null ){
		 contador = new Integer(0);
		}
		// Establecemos el contador como atributo del context bajo el nombre
		// contador. En caso de que ya existiera, sobreescribiría la referencia
		// existente con la nueva.
		getServletContext().setAttribute("contador",new Integer(contador.intValue()+1));
		
		if (listado == null){
			listado = new Vector();
		}
		
		if ( nombre != null ){
			out.println("<br>Hola "+nombre+"<br>");
			listado.addElement(nombre);
		}
		
		request.getSession().setAttribute("listado",listado);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");
		out.println("Bienvenido a mi primera página Web!");
		



		out.println("<br>");
		out.println("Contigo, hoy me han visitado:<br>");
		for ( int i = 0 ; i < listado.size() ; i++ ){
			out.println("<br>"+(String)listado.elementAt(i));
		}
		
		out.println("<br><br>");
		out.println("<a href=\"index.html\">Volver</a>");
		
		out.println("<br><br>" + contador +" visitas");

		
		out.println("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
