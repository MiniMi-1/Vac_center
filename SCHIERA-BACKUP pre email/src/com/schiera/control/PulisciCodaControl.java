package com.schiera.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.schiera.data.DBQuery;

/**
 * Servlet implementation class pulisciCodaControl
 */
@WebServlet("/PulisciCodaControl")
public class PulisciCodaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PulisciCodaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBQuery query = new DBQuery();
		
		query.pulisciCoda();
		
		response.sendRedirect(request.getContextPath() + "/VIEW/vistaimpiegato.jsp");
		
		
		
	}



}
