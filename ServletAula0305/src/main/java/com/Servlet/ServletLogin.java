package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/login", "/inserir", "/consultar"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod();
		if(method.equals("POST")) {
			doPost(request, response);
			System.out.println("Estou no service, método POST");
		}else if(method.equals("GET")) {
			doGet(request, response);
			System.out.println("Estou no service, método GET");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if(username.equals("Lucas")&&password.equals("123")) {
			out.print("Olá "+username+" sua senha é:"+password);
		}else {
			out.print("Não encontrado");
		}
	}

}
