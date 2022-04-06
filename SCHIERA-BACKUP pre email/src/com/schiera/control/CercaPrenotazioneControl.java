package com.schiera.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.schiera.data.*;
/**
 * Servlet implementation class CercaPrenotazioneControl
 */
@WebServlet("/CercaPrenotazioneControl")
public class CercaPrenotazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CercaPrenotazioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DBQuery query= new DBQuery();
		Prenotazione prenotazione = new Prenotazione();
		
		prenotazione.setCod_prenotazione(Integer.parseInt(request.getParameter("cod_prenotazione")));
		prenotazione.setCodice_fiscale(request.getParameter("cod_fiscale"));
		
		prenotazione = query.cercaPrenotazione(prenotazione.getCod_prenotazione(), prenotazione.getCodice_fiscale());
		
		if(prenotazione.getCod_prenotazione()>0) {
			//session.setAttribute("cod_prenotazione", prenotazione.getCod_prenotazione());
			session.setAttribute("prenotazione", prenotazione);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaprenotazione.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("errore", "Nessuna prenotazione trovata, controlla che i dati siano giusti" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/viewerrore.jsp");
			dispatcher.forward(request, response);
		}
	}

}
