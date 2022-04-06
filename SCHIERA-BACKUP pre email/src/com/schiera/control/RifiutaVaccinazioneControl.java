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
 * Servlet implementation class rifiutaVaccinazioneControl
 */
@WebServlet("/RifiutaVaccinazioneControl")
public class RifiutaVaccinazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RifiutaVaccinazioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * servlet chiamata nel caso in cui il medico decidesse di non 
     * vaccinare il paziente dopo aver visto le sue allergie/malattie
     */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DBQuery query = new DBQuery();
		Prenotazione prenotazione = (Prenotazione) session.getAttribute("prenotazione");
		
		int cod_prenotazione = prenotazione.getCod_prenotazione();
		
		query.rifiutaVaccinazione(cod_prenotazione);
		
		session.removeAttribute("prenotazione");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistamedico.jsp");
		dispatcher.forward(request, response);
		
	}

}
