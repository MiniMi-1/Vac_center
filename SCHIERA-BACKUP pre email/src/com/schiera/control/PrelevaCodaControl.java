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
 * Servlet implementation class prelevaCodaControl
 */
@WebServlet("/PrelevaCodaControl")
public class PrelevaCodaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrelevaCodaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		  servlet che preleva la prossima prenotazione in coda e la toglie dalla coda
		  se non trova prenotazioni in coda setta una variabile in request
		  per segnalarlo alla jsp di medico.
		 */
		DBQuery query = new DBQuery();
		Prenotazione prenotazione = new Prenotazione();
		
		HttpSession session = request.getSession();
		
		int cod_prenotazione=query.prelevaCoda();
		
		if (cod_prenotazione==-1) {
			
			request.setAttribute("coda_vuota", "vuota");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistamedico.jsp");
			dispatcher.forward(request, response);
			
		}
		else {
			
			query.togliDaCoda(cod_prenotazione);
			
			prenotazione = query.getPrenotazioneById(cod_prenotazione);
			
			session.setAttribute("prenotazione", prenotazione);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistamedico.jsp");
			dispatcher.forward(request, response);
			
		}
	}
}
