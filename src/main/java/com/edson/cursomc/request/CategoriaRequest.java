package com.edson.cursomc.request;

public class CategoriaRequest {

	private String nome;

	public CategoriaRequest() {
	}

	public CategoriaRequest(Integer id, String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
