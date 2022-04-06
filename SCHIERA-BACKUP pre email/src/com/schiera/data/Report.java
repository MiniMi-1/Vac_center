package com.schiera.data;

public class Report {

	private String nome;
	private String cognome;
	private String cod_fiscale;
	private String tipo_vaccino;
	private int count;
	
	public Report() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCod_fiscale() {
		return cod_fiscale;
	}

	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}

	public String getTipo_vaccino() {
		return tipo_vaccino;
	}

	public void setTipo_vaccino(String tipo_vaccino) {
		this.tipo_vaccino = tipo_vaccino;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
