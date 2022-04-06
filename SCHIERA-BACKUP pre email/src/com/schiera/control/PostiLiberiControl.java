package com.schiera.control;

import com.schiera.data.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class postiLiberi
 */
@WebServlet("/PostiLiberiControl")
public class PostiLiberiControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostiLiberiControl() {
        super();
        // TODO Auto-generated constructor stub
    }
/*
 * Controlla i posti liberi in una specifica data facendo un count
 * delle prenotazioni con quelle date e settando una variabile di
 * request show_form che usa la jsp per mostrare o meno il form di 
 * prenotazione
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int posti=2;
		int posti_disponibili;
		boolean show_form=false;
		DBQuery query = new DBQuery();
		String data= request.getParameter("data_check");
		
		System.out.println(data);
		
		request.setAttribute("sto_prenotando", "sto prenotando");
		posti_disponibili=query.postiLiberi(data);
		
		if(posti_disponibili>=posti) {
			
			request.setAttribute("show_form", show_form);
			request.setAttribute("no_posti", "nessun posto disponibile");
			
			System.out.println(posti_disponibili);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/index.jsp");
			dispatcher.forward(request, response);
		}
		else {
			show_form=true;
			
			request.setAttribute("data_check", data);
			request.setAttribute("show_form", show_form);
			
			System.out.println(posti_disponibili);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/index.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
