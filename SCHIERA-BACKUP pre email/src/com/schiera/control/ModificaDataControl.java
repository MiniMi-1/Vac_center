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
 * Servlet implementation class ModificaDataControl
 */
@WebServlet("/ModificaDataControl")
public class ModificaDataControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDataControl() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * controlla se ci sono posti e in caso modifica la data
		 * altrimenti rimanda indietro un messaggio
		 */
		
		HttpSession session = request.getSession();
		DBQuery query = new DBQuery();
		Prenotazione prenotazione = (Prenotazione) session.getAttribute("prenotazione");
		
		int posti=2;
		int posti_disponibili;
		String data = request.getParameter("data");
		
		posti_disponibili=query.postiLiberi(data);
		
		if(posti_disponibili>=posti) {
			request.setAttribute("errore", "Nessun posto disponibile");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaprenotazione.jsp");
			dispatcher.forward(request, response);
		}
		else {
			query.modificaData(prenotazione.getCod_prenotazione(), data);
		
			String data_modificata="modificata";
		
			prenotazione.setData(data);
		
			session.setAttribute("data", data);
			request.setAttribute("data_modificata", data_modificata);
		
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaprenotazione.jsp");
			dispatcher.forward(request, response);
			
		}
		
	}

}
