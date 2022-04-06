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
 * Servlet implementation class vaccinaControl
 */
@WebServlet("/vaccinaControl")
public class VaccinaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VaccinaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/*
	 * control per la vaccinazione, doGet per seconda dose e doPost per prima
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * prende la prenotazione dalla sessione e il vaccino da request
		 * se è not null allora lo salva nella variabile vaccino
		 * fa la query per vaccinare e distrugge l'attributo prenotazione 
		 */
		HttpSession session = request.getSession();
		
		DBQuery query = new DBQuery();
		Prenotazione prenotazione = (Prenotazione) session.getAttribute("prenotazione");
		
		String vaccino="tipo vaccino";
		int cod_medico=(int)(session.getAttribute("id_medico"));
		
		if(request.getParameter("vaccini") != null) {
			vaccino = request.getParameter("vaccini");
		}
		
		query.vaccinazione(prenotazione, vaccino, cod_medico);
		
		session.removeAttribute("prenotazione");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistamedico.jsp");
		dispatcher.forward(request, response);
		
	}
}
