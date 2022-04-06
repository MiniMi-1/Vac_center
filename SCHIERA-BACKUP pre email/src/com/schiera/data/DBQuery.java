package com.schiera.data;

import java.sql.*;

//import javax.servlet.http.HttpSession;


public class DBQuery {
	
	private String connectionURL = "jdbc:mysql://localhost:8889/schiera?serverTimezone=UTC";
	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	
	
	private String login = ("select cod_impiegato, nome, cognome, is_staff, is_asp from dipendente where cod_impiegato=? and nome=? and cognome=?");
	private String prenota= ("insert into prenotazione (cod_prenotazione, nome, cognome, codice_fiscale, is_prima_dose, is_completo, is_rifiutata, tipo_vaccino, malattie, data_vaccino, data_prima_dose) values (NULL,?,?,?,TRUE,FALSE,FALSE,NULL,?,?, NULL)");
	private String check_se_esiste=("select * from prenotazione where codice_fiscale=?");
	private String get_p_by_id=("select * from prenotazione where cod_prenotazione=?");
	private String cerca_new_p= ("select * from prenotazione where nome=? and cognome=? and codice_fiscale=?");
	private String cerca_p= ("select * from prenotazione where cod_prenotazione=? and codice_fiscale=?");
	private String modifica_p= ("update prenotazione set data_vaccino=? where cod_prenotazione=?");
	private String cancella_p = ("delete from prenotazione where cod_prenotazione=?");
	private String check_medico = ("select * from dipendente where cod_impiegato=?");
	private String nuovo_medico = ("insert into dipendente (cod_impiegato, nome, cognome, is_staff, is_asp) values (?,?,?,FALSE,FALSE)");
	private String check_data = ("select * from prenotazione where cod_prenotazione=? and data_vaccino = CURDATE()");
	private String in_coda= ("insert into coda_vaccino (coda, ref_prenotazione, is_servito) values (NULL,?,FALSE)");
	private String il_prossimo=("select ref_prenotazione from coda_vaccino where is_servito = FALSE order by coda limit 1");
	private String togli_da_coda=("update coda_vaccino set is_servito=TRUE where ref_prenotazione=?");
	private String pulisci_coda=("delete from coda_vaccino");
	
	private String prima_dose=("update prenotazione set data_prima_dose=CURDATE(), data_vaccino=DATE_ADD(data_vaccino, interval ? day), tipo_vaccino=?, is_prima_dose=FALSE, is_completo=? where cod_prenotazione=?");
	private String seconda_dose=("update prenotazione set is_completo=TRUE where cod_prenotazione=?");
	
	private String vaccinazione_effettuata=("insert into vacc_effettuata (ref_prenotazione, ref_dipendente, data_vaccino) values(?,?,CURDATE())");
	private String rifiuta_vaccinazione=("update prenotazione set is_rifiutata=TRUE where cod_prenotazione=?");
	private String verifica_posti = ("select count(*) as posti from prenotazione p where p.data_vaccino=?");
	
	private String almeno_una_dose=("select nome, cognome, codice_fiscale, tipo_vaccino, (select count(*) from prenotazione where is_prima_dose=FALSE) as conto from prenotazione where is_prima_dose=FALSE");
	private String ciclo_completato=("select nome, cognome, codice_fiscale, tipo_vaccino, (select count(*) from prenotazione where is_completo=TRUE) as conto from prenotazione where is_completo=TRUE");


	
	public DBQuery() {
		// TODO Auto-generated constructor stub
		// get a DB connection
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(connectionURL, "root", "root");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() {
		try{
			connection.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}			
	}

	public Medico login(int cod_impiegato, String nome, String cognome) {
		
		Medico medico_query = new Medico();
		
		 try{
			 	//statement = connection.prepareStatement(test);
				statement = connection.prepareStatement(login);
				statement.setInt(1, cod_impiegato);
				statement.setString(2, nome);
				statement.setString(3, cognome);
				rs = statement.executeQuery();
				
			
		
				if (rs.next()) {
					
					medico_query.setCod_impiegato(rs.getInt("cod_impiegato"));
					medico_query.setNome(rs.getString("nome"));
					medico_query.setCognome(rs.getString("cognome"));
					medico_query.setIs_staff(rs.getBoolean("is_staff"));
					medico_query.setIs_asp(rs.getBoolean("is_asp"));
					
				}
				
				else {
					medico_query.setCod_impiegato(-1);
				}
				rs.close();
				statement.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		
		return medico_query;
	}

	public boolean checkIfExists(String cod_fiscale) {
		boolean result=true;
		
		try {
			
			statement = connection.prepareStatement(check_se_esiste);
			statement.setString(1, cod_fiscale);
			rs=statement.executeQuery();
			
			if(rs.next()) {
				result = false;
				//se è false allora esiste già una prenotazione
			}	
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public Prenotazione nuovaPrenotazione(String nome, String cognome, String cod_fiscale, String malattie, String data) {
		
		Prenotazione prenotazione = new Prenotazione();
			
		//Date date=Date.valueOf(data);
		
		try {
			
			statement = connection.prepareStatement(prenota);
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, cod_fiscale);
			statement.setString(4, malattie);
			statement.setString(5, data);
			
			statement.executeUpdate();
			//rs.close();
			//statement.close();
			
			statement = connection.prepareStatement(cerca_new_p);
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, cod_fiscale);
			
			rs = statement.executeQuery();
			
			if(rs.next()) {
				
				prenotazione.setCod_prenotazione(rs.getInt("cod_prenotazione"));
				prenotazione.setNome(rs.getString("nome"));
				prenotazione.setCognome(rs.getString("cognome"));
				prenotazione.setCodice_fiscale(rs.getString("codice_fiscale"));
				prenotazione.setIs_prima_dose(rs.getBoolean("is_prima_dose"));
				prenotazione.setIs_completo(rs.getBoolean("is_completo"));
				prenotazione.setTipo_vaccino(rs.getString("tipo_vaccino"));
				prenotazione.setIs_prima_dose(rs.getBoolean("is_rifiutata"));
				prenotazione.setMalattie(rs.getString("malattie"));
				prenotazione.setData(rs.getString("data_vaccino"));	
				prenotazione.setData_prima_dose(rs.getString("data_prima_dose"));
				prenotazione.setIs_rifiutata(rs.getBoolean("is_rifiutata"));
			}
			
			rs.close();
			statement.close();	
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	return prenotazione;
	
	}

	public Prenotazione cercaPrenotazione(int cod_prenotazione, String cod_fiscale) {
		Prenotazione prenotazione = new Prenotazione();
		
		try {
			statement = connection.prepareStatement(cerca_p);
			statement.setInt(1, cod_prenotazione);
			statement.setString(2, cod_fiscale);
			rs=statement.executeQuery();
			
			if(rs.next()) {
				
				prenotazione.setCod_prenotazione(rs.getInt("cod_prenotazione"));
				prenotazione.setNome(rs.getString("nome"));
				prenotazione.setCognome(rs.getString("cognome"));
				prenotazione.setCodice_fiscale(rs.getString("codice_fiscale"));
				prenotazione.setIs_prima_dose(rs.getBoolean("is_prima_dose"));
				prenotazione.setIs_completo(rs.getBoolean("is_completo"));
				prenotazione.setIs_prima_dose(rs.getBoolean("is_rifiutata"));
				prenotazione.setTipo_vaccino(rs.getString("tipo_vaccino"));
				prenotazione.setMalattie(rs.getString("malattie"));
				prenotazione.setData(rs.getString("data_vaccino"));
				prenotazione.setData_prima_dose(rs.getString("data_prima_dose"));
				prenotazione.setIs_rifiutata(rs.getBoolean("is_rifiutata"));
				
			}
			
			else {
				prenotazione.setCod_prenotazione(-1);
			}
			
			rs.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prenotazione;
	}
	
	public void modificaData(int cod_prenotazione, String data) {
		
		try {
			
			statement = connection.prepareStatement(modifica_p);
			
			statement.setString(1, data);
			statement.setInt(2, cod_prenotazione);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void cancellaPrenotazione(int cod_prenotazione) {
		
		try {
			statement = connection.prepareStatement(cancella_p);
			statement.setInt(1, cod_prenotazione);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public boolean controllaCodiceMedico(int cod_medico) {
		
		boolean esiste = false;
		
		try {
			statement=connection.prepareStatement(check_medico);
			statement.setInt(1, cod_medico);
			rs=statement.executeQuery();
			if(rs.next()) {
				esiste=true;
				
				rs.close();
				//connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return esiste;
	}
	
	public void nuovoMedico(int cod_medico, String nome, String cognome) {
		
		try {
			statement=connection.prepareStatement(nuovo_medico);
			statement.setInt(1, cod_medico);
			statement.setString(2, nome);
			statement.setString(3,cognome);
			
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkData(int cod_prenotazione) {
		
		boolean result = false;
		
		try {
			statement = connection.prepareStatement(check_data);
			statement.setInt(1, cod_prenotazione);
			rs=statement.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
			
			rs.close();
			statement.close();

			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void inCoda(int cod_prenotazione) {
		
		try {
			
			statement = connection.prepareStatement(in_coda);
			statement.setInt(1, cod_prenotazione);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pulisciCoda() {
		
		try {
			
			statement = connection.prepareStatement(pulisci_coda);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int prelevaCoda() {
		
		int cod_prenotazione = -1;
		
		try {
			statement = connection.prepareStatement(il_prossimo);
			rs = statement.executeQuery();
			
			if(rs.next()) {
				
				cod_prenotazione = rs.getInt("ref_prenotazione");
			}
			rs.close();
			statement.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cod_prenotazione;
	}
	
	public void togliDaCoda(int cod_prenotazione) {
		
		try {
			
			statement = connection.prepareStatement(togli_da_coda);
			statement.setInt(1, cod_prenotazione);
			statement.executeUpdate();
			
			statement.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Prenotazione getPrenotazioneById(int cod_prenotazione) {
		
		Prenotazione prenotazione = new Prenotazione();
		
		try {
			statement=connection.prepareStatement(get_p_by_id);
			statement.setInt(1, cod_prenotazione);
			rs=statement.executeQuery();
			
			if(rs.next()) {
				
				prenotazione.setCod_prenotazione(rs.getInt("cod_prenotazione"));
				prenotazione.setNome(rs.getString("nome"));
				prenotazione.setCognome(rs.getString("cognome"));
				prenotazione.setCodice_fiscale(rs.getString("codice_fiscale"));
				prenotazione.setIs_prima_dose(rs.getBoolean("is_prima_dose"));
				prenotazione.setIs_completo(rs.getBoolean("is_completo"));
				prenotazione.setTipo_vaccino(rs.getString("tipo_vaccino"));
				prenotazione.setMalattie(rs.getString("malattie"));
				prenotazione.setData(rs.getString("data_vaccino"));
				prenotazione.setData_prima_dose(rs.getString("data_prima_dose"));
				prenotazione.setIs_rifiutata(rs.getBoolean("is_rifiutata"));
				rs.close();
				statement.close();
				connection.close();
				
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return prenotazione;
	}
	
	public void vaccinazione(Prenotazione prenotazione, String tipo_vaccino, int cod_medico) {
		
		//HttpSession session = request.getSession();
		//Prenotazione prenotazione = (Prenotazione) session.getAttribute("prenotazione");
		
		if(prenotazione.isIs_prima_dose()) {
			
			/*
			 * query prima dose, tipo vaccino, setta data prima dose CURDATE()
			 * a seconda del valore di
			 * tipo_vaccino calcola giorni seconda data
			 */
			
			int giorni;
			
			try {
				statement = connection.prepareStatement(prima_dose);
				
				if(tipo_vaccino.equals("pfizer")) {
					giorni=42; //giorni per la seconda dose
					statement.setInt(1, giorni);
					statement.setString(2, tipo_vaccino);
					statement.setBoolean(3, false);
					
				}
				else if(tipo_vaccino.equals("moderna")) {
					giorni=42;
					statement.setInt(1, giorni);
					statement.setString(2, tipo_vaccino);
					statement.setBoolean(3, false);
					
				}
				else if(tipo_vaccino.equals("astra_zeneca")) {
					giorni=63;
					statement.setInt(1, giorni);
					statement.setString(2, tipo_vaccino);
					statement.setBoolean(3, false);
					
				}
				else if(tipo_vaccino.equals("johnson_and_johnson")) {
					giorni=0;
					statement.setInt(1, giorni);
					statement.setString(2, tipo_vaccino);
					statement.setBoolean(3, true);
					//non ha seconda dose
				}
				
				statement.setInt(4, prenotazione.getCod_prenotazione());
				statement.executeUpdate();
				
				
				//connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			
			/*
			 * query seconda dose setta semplicemente is_completo a true
			 * preso cod_prenotazione da ingresso
			 */
			try {
				statement = connection.prepareStatement(seconda_dose);
				statement.setInt(1, prenotazione.getCod_prenotazione());
				statement.executeUpdate();
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			
			statement=connection.prepareStatement(vaccinazione_effettuata);
			statement.setInt(1, prenotazione.getCod_prenotazione());
			statement.setInt(2, cod_medico );
			statement.executeUpdate();
			
			statement.close();
			connection.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rifiutaVaccinazione(int cod_prenotazione) {
		
		try {
			statement = connection.prepareStatement(rifiuta_vaccinazione);
			statement.setInt(1, cod_prenotazione);
			statement.executeUpdate();
			
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public int postiLiberi(String date) {
		int posti_occupati=-1;
		
		try {
			statement=connection.prepareStatement(verifica_posti);
			statement.setString(1, date);
			rs=statement.executeQuery();
			
			if(rs.next()) {
				posti_occupati=rs.getInt("posti");
			}
			
			rs.close();
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return posti_occupati;
	}

	public Report[] reportAlmenoUnaDose() {
		
		//gli metto il count come size 
		Report[] report = null;
		
		try {
			statement=connection.prepareStatement(almeno_una_dose);
			rs=statement.executeQuery();
			if(rs.next()) {
				
				report = new Report[rs.getInt("conto")];
				
				for(int i=0; i<(rs.getInt("conto")); i++) {
					
					Report r=new Report();
					
					r.setCount(rs.getInt("conto"));
					r.setNome(rs.getString("nome"));
					r.setCognome(rs.getString("cognome"));
					r.setCod_fiscale(rs.getString("codice_fiscale"));
					r.setTipo_vaccino(rs.getString("tipo_vaccino"));
					report[i]=r;
					rs.next();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
	
public Report[] reportCicloCompletato() {
		
		//gli metto il count come size 
		Report[] report = null;
		
		try {
			statement=connection.prepareStatement(ciclo_completato);
			rs=statement.executeQuery();
			if(rs.next()) {
				
				report = new Report[rs.getInt("conto")];
				
				for(int i=0; i<(rs.getInt("conto")); i++) {
					
					Report r=new Report();
					
					r.setCount(rs.getInt("conto"));
					r.setNome(rs.getString("nome"));
					r.setCognome(rs.getString("cognome"));
					r.setCod_fiscale(rs.getString("codice_fiscale"));
					r.setTipo_vaccino(rs.getString("tipo_vaccino"));
					report[i]=r;
					rs.next();
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report;
	}
}
	





       
    

