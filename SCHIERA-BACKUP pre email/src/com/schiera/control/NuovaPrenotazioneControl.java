package com.schiera.control;

import com.schiera.data.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NuovaPrenotazioneControl
 */
@WebServlet("/NuovaPrenotazioneControl")
public class NuovaPrenotazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NuovaPrenotazioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

/*	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//doGet(request, response);
		HttpSession session = request.getSession();
		Prenotazione prenotazione = new Prenotazione();
		DBQuery query = new DBQuery();
		
		prenotazione.setNome(request.getParameter("nome"));
		prenotazione.setCognome(request.getParameter("cognome"));
		prenotazione.setCodice_fiscale(request.getParameter("cod_fiscale"));
		prenotazione.setData(request.getParameter("data"));
		
		//System.out.println(prenotazione.getNome() + prenotazione.getCognome() + prenotazione.getCodice_fiscale() + prenotazione.getData());
		if(query.checkIfExists(prenotazione.getCodice_fiscale())) {
			
			if(request.getParameter("malattie") == null) {
				prenotazione.setMalattie("Nessuna malattia/allergia segnalata");
			}
			else {
				prenotazione.setMalattie(request.getParameter("malattie"));
			}
		
			prenotazione = query.nuovaPrenotazione(prenotazione.getNome(), prenotazione.getCognome(), prenotazione.getCodice_fiscale(), prenotazione.getMalattie(), prenotazione.getData());
		
			//HttpSession session = request.getSession();
			//session.setAttribute("cod_prenotazione", prenotazione.getCod_prenotazione());
			session.setAttribute("prenotazione", prenotazione);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaprenotazione.jsp");
			dispatcher.forward(request, response);
			}
		
		else {
			request.setAttribute("errore", "esiste già una prenotazione a questo nome");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/viewerrore.jsp");
			dispatcher.forward(request, response);	
		}
		
	}
}
