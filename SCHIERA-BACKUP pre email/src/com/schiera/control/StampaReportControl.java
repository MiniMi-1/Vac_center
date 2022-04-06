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
 * Servlet implementation class StampaReportControl
 */
@WebServlet("/StampaReportControl")
public class StampaReportControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StampaReportControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * getParam dell'opzione
		 * if 1 anche prima dose (is_primadose=false)
		 * if 2 solo ciclo completo (is_completo=true)
		 * 
		 * chiama query.report e si fa restituire
		 * un oggetto report (count, nome, cognome, cod_fiscale, tipo_vaccino, data)
		 * se è prima dose(?)
		 */
		
		DBQuery query = new DBQuery();
		//String opzione = 
		Report[] report = null;
		
		if(request.getParameter("report").equals("1")) {
			
			report = query.reportAlmenoUnaDose();
			
		}
		else if(request.getParameter("report").equals("2")) {
			report = query.reportCicloCompletato();
		}
		
		if(report==null) {
			request.setAttribute("errore", "Ancora nessuna vaccinazione");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaasp.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			
			System.out.println(report[0].getCount());
			request.setAttribute("report", report);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistareport.jsp");
			dispatcher.forward(request, response);
		}
	}


}
