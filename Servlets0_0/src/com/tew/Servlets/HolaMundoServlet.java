package com.tew.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
    	
    	
    		 System.out.println("AAAAAAAA");
    		 response.setCharacterEncoding("UTF-8");
    		 response.setContentType("text/html");
    		 PrintWriter out = response.getWriter();
    		 out.println("<HTML>");
    		 out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
    		 out.println("<BODY>");
    		 out.println("Bienvenido a mi primera página web!");
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
