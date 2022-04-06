package com.schiera.control;

import com.schiera.data.*;
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class LoginControl
 */
@WebServlet({ "/LoginControl"})
public class LoginControl extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		DBQuery query = new DBQuery();
		//Medico medico = (Medico) request.getSession().getAttribute("medico");
		Medico medico_query= new Medico();
		
		int cod_i= Integer.parseInt(request.getParameter("cod_impiegato"));
		medico_query.setCod_impiegato(cod_i);
		medico_query.setNome(request.getParameter("nome_i"));
		medico_query.setCognome(request.getParameter("cognome_i"));
		
		//System.out.println(cod_i + " " + medico_query.getNome() + " " + medico_query.getCognome());
		
		medico_query = query.login(medico_query.getCod_impiegato(), medico_query.getNome(), medico_query.getCognome());
		
		if(medico_query.getCod_impiegato()< 0) {
		
		session.setAttribute("id_medico", medico_query.getCod_impiegato());
		request.setAttribute("errore", "Nessuna corrispondenza trovata, riprova");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/viewerrore.jsp");
		dispatcher.forward(request, response);	
		
		}
		
		else {
			
			if(medico_query.isIs_staff()) {
				
				session.setAttribute("id_medico", medico_query.getCod_impiegato());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaimpiegato.jsp");
				dispatcher.forward(request, response);
			
			}
			else if(medico_query.isIs_asp()) {
				
				session.setAttribute("id_medico", medico_query.getCod_impiegato());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistaasp.jsp");
				dispatcher.forward(request, response);
				
			}
			else {
				
				session.setAttribute("id_medico", medico_query.getCod_impiegato());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/VIEW/vistamedico.jsp");
				dispatcher.forward(request, response);
			
			}
			
		}
	
	}

}

