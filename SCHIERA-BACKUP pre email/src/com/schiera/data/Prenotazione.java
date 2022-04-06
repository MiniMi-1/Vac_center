package com.schiera.data;

public class Prenotazione {

	private int cod_prenotazione;
	private String nome;
	private String Cognome;
	private String codice_fiscale;
	private boolean is_prima_dose;
	private boolean is_completo;
	private boolean is_rifiutata;
	private String tipo_vaccino;
	private String malattie;
	private String data;
	private String data_prima_dose;
	
	
	public Prenotazione() {
		// TODO Auto-generated constructor stub
	}


	public String getCognome() {
		return Cognome;
	}


	public void setCognome(String cognome) {
		Cognome = cognome;
	}


	public int getCod_prenotazione() {
		return cod_prenotazione;
	}


	public void setCod_prenotazione(int cod_prenotazione) {
		this.cod_prenotazione = cod_prenotazione;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCodice_fiscale() {
		return codice_fiscale;
	}


	public void setCodice_fiscale(String codice_fisale) {
		this.codice_fiscale = codice_fisale;
	}


	public boolean isIs_prima_dose() {
		return is_prima_dose;
	}


	public void setIs_prima_dose(boolean is_prima_dose) {
		this.is_prima_dose = is_prima_dose;
	}


	public boolean isIs_completo() {
		return is_completo;
	}


	public void setIs_completo(boolean is_completo) {
		this.is_completo = is_completo;
	}


	public String getTipo_vaccino() {
		return tipo_vaccino;
	}


	public void setTipo_vaccino(String tipo_vaccino) {
		this.tipo_vaccino = tipo_vaccino;
	}


	public String getMalattie() {
		return malattie;
	}


	public void setMalattie(String malattie) {
		this.malattie = malattie;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getData_prima_dose() {
		return data_prima_dose;
	}


	public void setData_prima_dose(String data_prima_dose) {
		this.data_prima_dose = data_prima_dose;
	}


	public boolean isIs_rifiutata() {
		return is_rifiutata;
	}


	public void setIs_rifiutata(boolean is_rifiutata) {
		this.is_rifiutata = is_rifiutata;
	}

}
