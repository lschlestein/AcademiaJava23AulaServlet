package com.BancoDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.Models.Pessoa;

public class BancoDados {
	Connection c= null;
	String url;
	String user;
	String password;
	
	public  BancoDados(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password; 
	}
	
	public Connection conectar() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(url,user,password);
			System.out.println("Conectado");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro ao conectar"+e);
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	public boolean consultarPessoa(Pessoa p) {
		if(c==null) {
			this.c = conectar();
		}else {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM students WHERE name=? and password=?");
				System.out.println(p);
				ps.setString(1, p.getNome());
				ps.setString(2, p.getPassword());
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Erro ao consultar"+e);
			}
			
		}
		return false;
	}

}
