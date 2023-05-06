package com.Servlet;

import java.io.IOException;
import com.Models.*;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.BancoDados.BancoDados;

@WebServlet(urlPatterns = { "/login", "/home", "/listar" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BancoDados bd = new BancoDados("jdbc:mysql://localhost:3306/student", "root", "");

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equals("POST")) {
			doPost(request, response);
			System.out.println("Estou no service, método POST");
		} else if (method.equals("GET")) {
			doGet(request, response);
			System.out.println("Estou no service, método GET");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/home")) {
			response.sendRedirect("index.html");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/listar")) {
			request.setAttribute("listaPessoas", bd.buscarPessoas());
			RequestDispatcher rd = request.getRequestDispatcher("pessoas.jsp");
			rd.forward(request, response);
		} else if (action.equals("/login")) {

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Pessoa p = new Pessoa(username, password);

			bd.conectar();

			PrintWriter out = response.getWriter();
			if (bd.consultarPessoa(p)) {
				out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title> Servlet Login</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Servlet Login</h1>");
				out.println("Olá " + p.getNome() + " sua senha é:" + p.getPassword());
				out.println("<a href=\"home\">Voltar</a>");
				out.println("</body>");
				out.println("</html>");

			} else {
				out.println("<!doctype html>");
				out.println("<html>");
				out.println("<head>");
				out.println("<title> Servlet Login</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>Servlet Login</h1>");
				out.println("Não Encontrado");
				out.println("<a href=\"home\">Voltar</a>");
				out.println("</body>");
				out.println("</html>");
			}
		}
	}

}
