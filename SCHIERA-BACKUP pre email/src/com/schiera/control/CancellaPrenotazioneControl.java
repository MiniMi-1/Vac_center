package com.schiera.control;

import com.schiera.data.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CancellaPrenotazioneControl
 */
@WebServlet("/CancellaPrenotazioneControl")
public class CancellaPrenotazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaPrenotazioneControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		DBQuery query = new DBQuery();
		Prenotazione prenotazione = (Prenotazione) session.getAttribute("prenotazione");
		
		query.cancellaPrenotazione(prenotazione.getCod_prenotazione());
		
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/VIEW/index.jsp");
	
	
	}
}

	
