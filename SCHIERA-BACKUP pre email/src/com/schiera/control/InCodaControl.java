package com.schiera.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schiera.data.DBQuery;

/**
 * Servlet implementation class inCodaControl
 */
@WebServlet("/InCodaControl")
public class InCodaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InCodaControl() {
        super();
  
    }

    /*
     Controlla prima se esiste una prenotazione alla data di oggi,
     se sì inserisce quel codice prenotazione in una coda
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DBQuery query = new DBQuery();
		int cod_prenotazione = Integer.parseInt(request.getParameter("cod_prenotazione"));
		
		if(query.checkData(cod_prenotazione)) {
			
			query.inCoda(cod_prenotazione);
			request.setAttribute("messaggio", "Prenotazione inserita in coda");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaimpiegato.jsp");
			dispatcher.forward(request, response);	
			
		}
		else {
			request.setAttribute("messaggio", "Il codice non è valido o la vaccinazione non è prevista per oggi");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaimpiegato.jsp");
			dispatcher.forward(request, response);	
		}
		
	}

}
