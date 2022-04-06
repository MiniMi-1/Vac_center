package com.schiera.data;


public class Medico {


	private int cod_impiegato;
	private String nome;
	private String cognome;
	private boolean is_staff = false;
	private boolean is_asp = false;
	
	public Medico() {
		// TODO Auto-generated constructor stub
	}

	

	public int getCod_impiegato() {
		return cod_impiegato;
	}

	public void setCod_impiegato(int cod_impiegato) {
		this.cod_impiegato = cod_impiegato;
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

	public boolean isIs_staff() {
		return is_staff;
	}

	public void setIs_staff(boolean is_staff) {
		this.is_staff = is_staff;
	}

	public boolean isIs_asp() {
		return is_asp;
	}

	public void setIs_asp(boolean is_asp) {
		this.is_asp = is_asp;
	}
	

}
