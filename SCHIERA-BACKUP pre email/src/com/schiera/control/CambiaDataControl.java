package com.schiera.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CambiaDataControl
 */
@WebServlet("/CambiaDataControl")
public class CambiaDataControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiaDataControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 * semplice servlet che fa tornare alla prima parte del form di prenotazione
	 * lo scopo principale è quello di "resettare" la request per mostrare
	 * il form di cambio data
	 */
		request.setAttribute("sto_prenotando", "sto prenotando");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/index.jsp");
		dispatcher.forward(request, response);
	}
}
