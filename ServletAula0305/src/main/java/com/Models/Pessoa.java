package com.Models;

public class Pessoa {
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", password=" + password + "]";
	}
	long id;
	String nome;
	String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Pessoa(String nome, String password) {
		super();
		this.nome = nome;
		this.password = password;
	}
	public Pessoa() {
	}

}
