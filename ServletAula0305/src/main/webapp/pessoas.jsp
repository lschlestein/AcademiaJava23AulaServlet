<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page import="com.Models.Pessoa" %>
    <%@page import="java.util.ArrayList" %>
    
    <%
    	@ SuppressWarnings("unchecked")
    	ArrayList<Pessoa> lista = (ArrayList<Pessoa>) request.getAttribute("listaPessoas");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Listagem de Pessoas</title>
</head>
<body>
	<h1>Lista de Pessoas Cadastradas</h1>
	<table>
		<thead>
			<tr>
				<th>Nome</th>
				<th>Senha</th>
			</tr>
		</thead>

		<tbody>
			<%for(int i=0; i<lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getPassword() %></td>
				</tr>
				<%} %>
		</tbody>
	</table>
	<p>
	<a href="home">Sair</a>
</body>
</html>