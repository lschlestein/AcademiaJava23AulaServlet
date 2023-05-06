package com.BancoDados;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.Models.Pessoa;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import java.util.*;

public class BancoDados {
	Connection c = null;
	String url;
	String user;
	String password;

	public BancoDados(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public Connection conectar() {
		if (c == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				c = DriverManager.getConnection(url, user, password);
				System.out.println("Conectado");
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Erro ao conectar" + e);
				e.printStackTrace();
			}
		}
		return c;
	}
	
	public Connection desconectar() {
		if(c!=null) {
			try {
				c.close();
				c=null;
			} catch (SQLException e) {
				System.out.println("Falha ao desconectar"+e);			}
		}
		return c;
	}

	public List<Pessoa> buscarPessoas() {
		List<Pessoa> lista = new ArrayList<>();
		Pessoa p = new Pessoa();		
		this.c = conectar();
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM students");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				p.setNome(rs.getString("name"));
				p.setPassword(rs.getString("password"));
				lista.add(p);
				System.out.println(p);
				p = new Pessoa();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar" + e);
		}
		this.c = desconectar();
		return lista;
	}

	public boolean consultarPessoa(Pessoa p) {
		if (c == null) {
			this.c = conectar();
		} else {
			try {
				PreparedStatement ps = c.prepareStatement("SELECT * FROM students WHERE name=? and password=?");
				System.out.println(p);
				ps.setString(1, p.getNome());
				ps.setString(2, p.getPassword());
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				System.out.println("Erro ao consultar" + e);
			}

		}
		return false;
	}

}
