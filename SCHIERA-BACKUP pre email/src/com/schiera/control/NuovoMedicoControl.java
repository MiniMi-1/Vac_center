package com.schiera.control;

import com.schiera.data.*;
import java.lang.Math;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NuovoMedicoControl
 */
@WebServlet("/NuovoMedicoControl")
public class NuovoMedicoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public NuovoMedicoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		Crea un codice random, vede se esiste già e in caso lo
		incrementa di uno fino a quando non trova un codice unico
		quindi lo inserisce nel db e restituisce cod, nome e cognome
		per visualizzarli nella pagina asp
		*/
		
		DBQuery query = new DBQuery();
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		
		int max=9999;
		int min=1000;
		int range= (max-min);
		int cod_medico = (int) (Math.random()*range)+min ;
		//così è sempre a 4 cifre
		
		boolean esiste = false;
		
		System.out.println(cod_medico);
			
		do {
			esiste = query.controllaCodiceMedico(cod_medico);
			
			if(esiste) {
				
				if(cod_medico==9999) {
					cod_medico=1000;
				}
				else {
					cod_medico++;
				}
			}
			
		}
		while(esiste);
		
		query.nuovoMedico(cod_medico, nome, cognome);
		
		request.setAttribute("cod_medico", cod_medico);
		request.setAttribute("nome", nome);
		request.setAttribute("cognome", cognome);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaasp.jsp");
		dispatcher.forward(request, response);
	}

}
